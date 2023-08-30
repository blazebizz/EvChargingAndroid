package com.blaze.feature.startup.screen.otp


import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreUiViewModel
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
    coreUi: CoreUiViewModel,
) {
    val TAG = "OtpScreen"
    val activity = LocalContext.current as Activity
    val context = LocalContext.current

    val otpState = remember {
        mutableStateOf("")
    }
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

                        Toast.makeText(
                            context,
                            "Logged In with ${task.result.user?.phoneNumber} name: ${task.result.user?.displayName}",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(DashboardRoute.DashboardScreen.route) {
                            popUpTo(StartUpRoute.MobileOtpScreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        when (task.exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                // Invalid request
                                Toast.makeText(context, "Invalid Otp", Toast.LENGTH_SHORT).show()
                            }

                            is FirebaseTooManyRequestsException -> {
                                // The SMS quota for the project has been exceeded
                                Toast.makeText(context, "SMS quota exceeded", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is FirebaseAuthMissingActivityForRecaptchaException -> {
                                // reCAPTCHA verification attempted with null Activity
                                Toast.makeText(
                                    context,
                                    "reCAPTCHA verification failed ! You are not a human",
                                    Toast.LENGTH_SHORT
                                ).show()
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
                    Toast.makeText(context, "Invalid Otp", Toast.LENGTH_SHORT).show()
                }

                is FirebaseTooManyRequestsException -> {
                    // The SMS quota for the project has been exceeded
                    Toast.makeText(context, "SMS quota exceeded", Toast.LENGTH_SHORT).show()
                }

                is FirebaseAuthMissingActivityForRecaptchaException -> {
                    // reCAPTCHA verification attempted with null Activity
                    Toast.makeText(
                        context,
                        "reCAPTCHA verification failed ! You are not a human",
                        Toast.LENGTH_SHORT
                    ).show()
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
                Toast.makeText(
                    context,
                    "code sent ${otpViewModel.storedVerificationId.value}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



    LaunchedEffect(key1 = Unit) {
        otpViewModel.sendVerificationCode(
            activity, toSentText, callbacks
        )
    }

    OtpContent(
        otpState = otpState,
        sentTo = toSentText,
        navController = navController,
        onSubmitClick = {
            Toast.makeText(context, "verifying please wait.", Toast.LENGTH_SHORT).show()
            ioScope.launch {
                otpViewModel.signInWithPhoneAuthCredential(activity, otpState.value) { task ->
                    mainScope.launch {
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Logged In with ${task.result.user?.phoneNumber} name: ${task.result.user?.displayName}",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(DashboardRoute.DashboardScreen.route) {
                                popUpTo(StartUpRoute.MobileOtpScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            if (task.exception is FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(context, "Invalid Otp", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
//            otpViewModel.verifyPhoneNumberWithCode(
//                otpState.value
//            )
        },
        onResendClick = {
            otpViewModel.reSendVerificationCode(
                activity, toSentText, callbacks
            )
        }
    )
}

@Composable
internal fun OtpContent(
    sentTo: String,
    otpState: MutableState<String>,
    navController: NavController,
    onSubmitClick: () -> Unit,
    onResendClick: () -> Unit,
) {
    var seconds by rememberSaveable {
        mutableIntStateOf(0)
    }
    var trigger by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = trigger) {
        delay(1000)
        while (seconds > 0) {
            delay(1000)
            seconds--
        }
    }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier.clickable {
                    navController.navigate(DashboardRoute.DashboardScreen.route)
                })
            Spacer(Modifier.width(12.dp))
            Text(text = "", fontWeight = FontWeight.Black, fontSize = 22.sp)
            Spacer(Modifier.weight(3f))
        }
        Spacer(Modifier.weight(3f))
//        Text(
//            text = "Verify OTP",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Black
//        )
//        Spacer(Modifier.height(12.dp))
        Text(
            text = "We have sent an SMS to",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(12.dp))
        Text(text = sentTo, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
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

                    Text(text = "$seconds seconds")
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Resend",
                        color = if (seconds == 0) Color.LightGray else Color.LightGray,
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
//        Spacer(Modifier.height(12.dp))
//        Text(
//            text = "Change Number.",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center
//        )

        Spacer(Modifier.weight(3f))
        Spacer(Modifier.height(20.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = onSubmitClick) {
            Text(text = "Submit")
        }
        Spacer(Modifier.weight(4f))
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun OtpView(length: Int, code: MutableState<String>) {
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BasicTextField(
            value = code.value,
            onValueChange = { otpNo ->
                if (otpNo.length <= length)
                    code.value = otpNo.filter { it.isDigit() }
                if (otpNo.length == length) {
                    keyboardController?.hide()
                }
            },
            Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                CodeInputDecoration(code.value, length)
            })
    }
}

@Composable
private fun CodeInputDecoration(code: String, length: Int) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .border(
                border = BorderStroke(2.dp, color = Color.Transparent),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Row {
            for (i in 0 until length) {
                val text = if (i < code.length) code[i].toString() else ""
                CodeEntry(text)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
private fun CodeEntry(text: String) {
    Box(
        modifier = Modifier
//            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .width(40.dp)
            .height(40.dp)
//            .clip(shape = RoundedCornerShape(8.dp))
//            .background(Color.White)
        ,

        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = text, fontSize = 20.sp)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }

    }
}

// filo