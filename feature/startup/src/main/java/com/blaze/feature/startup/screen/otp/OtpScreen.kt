package com.blaze.feature.startup.screen.otp


import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.CustomButtonColor
import com.blaze.core.ui.components.OtpView
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.mainScope
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpScreen(
    navController: NavController,
    toSentText: String,
    otpViewModel: OtpScreenViewModel,
    coreUi: CoreViewModel,
) {
    val TAG = "OtpScreen"
    val activity = LocalContext.current as Activity
    val context = LocalContext.current

    val otpState = remember {
        mutableStateOf("")
    }
    val otpSent = remember { mutableStateOf(false) }
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            otpViewModel.signInWithPhoneAuthCredential(activity, otpState.value) { task ->
                mainScope.launch {
                    if (task.isSuccessful) {
                        coreUi.snackbar("Logged In with ${task.result.user?.phoneNumber} name: ${task.result.user?.displayName}")
//                        Toast.makeText(
//                            context,
//                            "Logged In with ${task.result.user?.phoneNumber} name: ${task.result.user?.displayName}",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        navController.navigate(DashboardRoute.DashboardScreen.route) {
                            popUpTo(StartUpRoute.MobileOtpScreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        when (task.exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                // Invalid request
                                coreUi.snackbar("Invalid Otp")
//                                Toast.makeText(context, "Invalid Otp", Toast.LENGTH_SHORT).show()
                            }

                            is FirebaseTooManyRequestsException -> {
                                // The SMS quota for the project has been exceeded
                                coreUi.snackbar("SMS quota exceeded")
//                                Toast.makeText(context, "SMS quota exceeded", Toast.LENGTH_SHORT)
//                                    .show()
                            }

                            is FirebaseAuthMissingActivityForRecaptchaException -> {
                                // reCAPTCHA verification attempted with null Activity
                                coreUi.snackbar("reCAPTCHA verification failed ! You are not a human")
//                                Toast.makeText(
//                                    context,
//                                    "reCAPTCHA verification failed ! You are not a human",
//                                    Toast.LENGTH_SHORT
//                                ).show()
                            }
                        }
                    }
                }
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    // Invalid request
                    coreUi.snackbar("Invalid OTP")
//                    Toast.makeText(context, "Invalid Otp", Toast.LENGTH_SHORT).show()
                }

                is FirebaseTooManyRequestsException -> {
                    // The SMS quota for the project has been exceeded
                    coreUi.snackbar("SMS quota exceeded")
//                    Toast.makeText(context, "SMS quota exceeded", Toast.LENGTH_SHORT).show()
                }

                is FirebaseAuthMissingActivityForRecaptchaException -> {
                    // reCAPTCHA verification attempted with null Activity
                    coreUi.snackbar("reCAPTCHA verification failed ! You are not a human")
                }
            }
            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            otpViewModel.storedVerificationId.value = verificationId
            otpViewModel.resendToken.value = token
            mainScope.launch {
                coreUi.snackbar("Verification SMS Sent")
            }
            otpSent.value = true
        }
    }



    LaunchedEffect(key1 = Unit) {
        coreUi.snackbar("Sending Verification Code Please Wait.")
        otpViewModel.sendVerificationCode(
            activity, toSentText, callbacks
        )
    }

    OtpContent(otpState = otpState,
        sentTo = toSentText,
        navController = navController,
        otpSent = otpSent,
        onSubmitClick = {
            coreUi.snackbar("Verifying please wait.")
            ioScope.launch {
                try {
                    otpViewModel.signInWithPhoneAuthCredential(activity, otpState.value) { task ->
                        mainScope.launch {
                            if (task.isSuccessful) {

                                coreUi.snackbar("Logged In with ${task.result.user?.phoneNumber} name: ${task.result.user?.displayName}")
                                coreUi.currentUserNumber.value = task.result.user?.phoneNumber ?: ""
                                navController.navigate(DashboardRoute.DashboardScreen.route) {
                                    popUpTo(StartUpRoute.MobileOtpScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                                    coreUi.snackbar("Invalid OTP")
                                }
                            }
                        }
                    }

                } catch (e: Exception) {
                    coreUi.snackbar("Unable to verify the user. Please try again later.")
                }
            }
        },
        onResendClick = {
            otpViewModel.reSendVerificationCode(
                activity = activity,
                phoneNumber = toSentText,
                callbacks = callbacks
            )
        })
}

@Composable
internal fun OtpContent(
    sentTo: String,
    otpSent: MutableState<Boolean>,
    otpState: MutableState<String>,
    navController: NavController,
    onSubmitClick: () -> Unit,
    onResendClick: () -> Unit,
) {
    var seconds by rememberSaveable {
        mutableIntStateOf(30)
    }
    var trigger by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = trigger, key2 = otpSent.value) {
        if (otpSent.value) {
//            delay(1000)
            while (seconds > 0) {
                delay(1000)
                seconds--
            }
        }
    }


    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier.clickable {
                    navController.navigate(DashboardRoute.DashboardScreen.route)
                },
                tint = MaterialTheme.colorScheme.background
            )
            Spacer(Modifier.width(12.dp))
            Text(text = "", fontWeight = FontWeight.Black, fontSize = 22.sp)
            Spacer(Modifier.weight(3f))
        }
        Spacer(Modifier.weight(1f))
//        Text(
//            text = "Verify OTP",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Black
//        )
//        Spacer(Modifier.height(12.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            Spacer(Modifier.height(12.dp))

            Text(
                text = "Verify OTP",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.background
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "We have sent an SMS to",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.background
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = sentTo, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.background
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    OtpView(length = 6, code = otpState)
                    Spacer(Modifier.height(12.dp))
                    Row {

                        Text(
                            text = "$seconds seconds",
                            color = MaterialTheme.colorScheme.background
                        )
                        Spacer(Modifier.weight(1f))
                        Text(text = "Resend",
                            color = if (seconds == 0) MaterialTheme.colorScheme.background else Color.LightGray,
                            modifier = Modifier.clickable {
                                if (seconds == 0) {
                                    onResendClick.invoke()
                                    trigger = !trigger
                                    seconds = 30
                                }
                            })
                    }
                }
            }
            Spacer(Modifier.height(20.dp))

            Button(
                text = "Submit", modifier = Modifier.fillMaxWidth(), onClick = onSubmitClick,
                colors = CustomButtonColor(
                    bodyColor = MaterialTheme.colorScheme.background,
                    textColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
//        Spacer(Modifier.height(12.dp))
//        Text(
//            text = "Change Number.",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center
//        )

        Spacer(Modifier.height(20.dp))

    }
}


// filo