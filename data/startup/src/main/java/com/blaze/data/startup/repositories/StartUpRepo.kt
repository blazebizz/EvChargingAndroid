package com.blaze.data.startup.repositories

import android.app.Activity
import com.blaze.data.startup.model.req.CreateUserRequest
import com.blaze.data.startup.model.req.GenerateTokenRequest
import com.blaze.data.startup.model.res.CreateUserResponse
import com.blaze.data.startup.model.res.GenerateTokenResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.velox.lazeir.utils.handler.NetworkResource
import kotlinx.coroutines.flow.Flow

interface StartUpRepo {

    fun sendOtp(
        activity: Activity,
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    )

    fun verifyPhoneNumberWithCode(verificationId: String, code: String): PhoneAuthCredential

    fun resendVerificationCode(
        activity: Activity,
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
    )

    fun getAuth(): FirebaseAuth

    fun signInWithPhoneAuthCredential(activity: Activity, credential: PhoneAuthCredential, onComplete:(Task<AuthResult>)->Unit)
    suspend fun generateToken(body: GenerateTokenRequest): Flow<NetworkResource<GenerateTokenResponse>>
    suspend fun createUser(body: CreateUserRequest): Flow<NetworkResource<CreateUserResponse>>
}