package com.blaze.feature.startup.screen.otp

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blaze.core.utils.util.handleFlowInt
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.mainScope
import com.blaze.data.startup.model.req.GenerateTokenRequest
import com.blaze.data.startup.model.res.GenerateTokenResponse
import com.blaze.data.startup.repositories.StartUpRepo
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.velox.lazeir.utils.outlet.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OtpScreenViewModel @Inject constructor(private val repo: StartUpRepo) : ViewModel() {

    val resendToken = mutableStateOf<PhoneAuthProvider.ForceResendingToken?>(null)
    val storedVerificationId: MutableState<String> = mutableStateOf("")
    private val TAG = "OtpScreenViewModel"

    fun sendVerificationCode(
        activity: Activity,
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        ioScope.launch {
            repo.sendOtp(
                activity = activity, phoneNumber = phoneNumber, callbacks = callbacks
            )
        }
    }

    fun reSendVerificationCode(
        activity: Activity,
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        ioScope.launch {
            repo.resendVerificationCode(
                activity = activity,
                phoneNumber = phoneNumber,
                token = resendToken.value,
                callbacks = callbacks
            )
        }
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, context: Activity) {
        ioScope.launch {
            repo.getAuth().signInWithCredential(credential).addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
        }
    }

    fun verifyPhoneNumberWithCode(
        code: String
    ) {
        ioScope.launch {
            storedVerificationId.value.let { repo.verifyPhoneNumberWithCode(it, code) }
        }
    }

    fun signInWithPhoneAuthCredential(
        activity: Activity, otp: String, onComplete: (Task<AuthResult>) -> Unit
    ) {
        repo.signInWithPhoneAuthCredential(
            activity = activity,
            credential = PhoneAuthProvider.getCredential(storedVerificationId.value, otp)
        ) {
            onComplete(it)
        }
    }

    fun generateToken(
        body: GenerateTokenRequest,
        onSuccess: (res: GenerateTokenResponse) -> Unit,
        onFailure: (String, Int) -> Unit = { _, _ -> },
        loading: MutableState<Boolean>
    ) {
        ioScope.launch {
            loading.value = true
            repo.generateToken(body).handleFlowInt(onLoading = {
                loading.value = it
            }, onFailure = { msg, obj, _ ->
                Log.e(TAG, "generateToken: $obj")

                var message: String = msg
                var status = -1

                message = obj.getString("message")
                status = obj.getInt("status")

                onFailure(message, status)
            }, onSuccess = {
                mainScope.launch {
                    onSuccess(it)
                }
            })
        }
    }
}