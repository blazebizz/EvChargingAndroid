package com.blaze.feature.startup.screen.otp

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blaze.data.startup.repositories.StartUpRepo
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OtpScreenViewModel @Inject constructor(private val repo: StartUpRepo) : ViewModel() {

    val resendToken = mutableStateOf<PhoneAuthProvider.ForceResendingToken?>(null)
    val storedVerificationId: MutableState<String?> = mutableStateOf(null)
    private val TAG = "OtpScreenViewModel"

    fun sendVerificationCode(
        activity: Activity,
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        repo.sendOtp(
            activity = activity,
            phoneNumber = phoneNumber,
            callbacks = callbacks
        )
    }

    fun reSendVerificationCode(
        activity: Activity, phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        repo.resendVerificationCode(
            activity = activity,
            phoneNumber = phoneNumber,
            token = resendToken.value,
            callbacks = callbacks
        )
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, context: Activity) {
        repo.getAuth().signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
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