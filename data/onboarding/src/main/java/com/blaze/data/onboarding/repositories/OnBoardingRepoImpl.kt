package com.blaze.data.onboarding.repositories

import com.blaze.core.utils.util.globalError
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.mainScope
import com.blaze.data.onboarding.apiservice.OnBoardingApiService
import com.blaze.data.onboarding.model.req.FetchPartnerOnBoardDataRequest
import com.blaze.data.onboarding.model.req.PartnerOnBoardRequest
import com.blaze.data.onboarding.model.res.FetchPartnerOnBoardDataResponse
import com.blaze.data.onboarding.model.res.PartnerOnBoardResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.velox.lazeir.utils.handler.NetworkResource
import com.velox.lazeir.utils.outlet.handleNetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingRepoImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val apiService: OnBoardingApiService
) : OnBoardingRepo {

    override fun getAuth() = auth

    override suspend fun fetchUserOnBoardData(body: FetchPartnerOnBoardDataRequest): Flow<NetworkResource<FetchPartnerOnBoardDataResponse>> {
        return try {
            apiService.fetchUserOnBoardData(body).handleNetworkResponse()
        } catch (e: Exception) {
            flow { emit(NetworkResource.Error(globalError(this::class.java.simpleName))) }
        }

    }

    override fun uploadImage(
        userId: String,
        imageId: String,
        imageByteArray: ByteArray,
        onFailure: (String?) -> Unit,
        onSuccess: (String?) -> Unit
    ) {
        ioScope.launch {
            val storage = Firebase.storage
            val reference =
                storage.reference.child("OnBoardPartner").child(userId).child("$imageId.jpg")
            val uploadTask = reference.putBytes(imageByteArray)

            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                mainScope.launch {
                    onFailure("Failed to upload image")
                }
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                if (taskSnapshot != null) {
                    reference.downloadUrl.addOnSuccessListener {
                        mainScope.launch {
                            onSuccess(it.toString())
                        }
                    }.addOnFailureListener {
                        mainScope.launch {
                            onFailure("Failed to get image url")
                        }
                    }
                }
            }
        }
    }

    override suspend fun onBoard(body: PartnerOnBoardRequest): Flow<NetworkResource<PartnerOnBoardResponse>> {

        return try {
            apiService.onBoardUser(body).handleNetworkResponse()
        } catch (e: Exception) {
            flow { emit(NetworkResource.Error(globalError(this::class.java.simpleName))) }
        }
    }


}