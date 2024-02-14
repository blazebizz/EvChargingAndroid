package com.blaze.data.startup.repositories

import android.app.Activity
import com.blaze.core.utils.util.globalError
import com.blaze.core.utils.util.handleNetworkResponseInternal
import com.blaze.data.startup.apiservice.StartUpApiService
import com.blaze.data.startup.model.req.CreateUserRequest
import com.blaze.data.startup.model.req.GenerateTokenRequest
import com.blaze.data.startup.model.res.CreateUserResponse
import com.blaze.data.startup.model.res.GenerateTokenResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.velox.lazeir.utils.handler.RetrofitResource
import com.velox.lazeir.utils.outlet.handleNetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StartUpRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val apiService: StartUpApiService,
) : StartUpRepo {

    override fun getAuth() = auth
    override fun sendOtp(
        activity: Activity,
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {

        val options =
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity) // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyPhoneNumberWithCode(
        verificationId: String, code: String
    ): PhoneAuthCredential {
        // [START verify_with_code]
        return PhoneAuthProvider.getCredential(verificationId, code)
        // [END verify_with_code]
    }

    // [START resend_verification]
    override fun resendVerificationCode(
        activity: Activity,
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
    ) {
        val optionsBuilder =
            PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phoneNumber) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity) // (optional) Activity for callback binding
                // If no activity is passed, reCAPTCHA verification can not be used.
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }


     override fun signInWithPhoneAuthCredential(activity: Activity, credential: PhoneAuthCredential, onComplete:(Task<AuthResult>)->Unit) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                onComplete(task)
            }
    }
    // [END resend_verification]

   override suspend fun generateToken(body: GenerateTokenRequest): Flow<RetrofitResource<GenerateTokenResponse>> {
       return try {
           apiService.generateToken(body).handleNetworkResponseInternal()
       }catch (e:Exception){
           flow { emit(RetrofitResource.Error(globalError(this::class.java.simpleName))) }
       }
    }

    override suspend fun createUser(body: CreateUserRequest): Flow<RetrofitResource<CreateUserResponse>> {
        return try {
            apiService.createUser(body).handleNetworkResponseInternal()
        }catch (e:Exception){
            flow { emit(RetrofitResource.Error(globalError(this::class.java.simpleName))) }
        }
    }


}