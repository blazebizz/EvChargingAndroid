package com.blaze.data.onboarding.repositories

import android.net.Uri
import com.blaze.data.onboarding.model.req.FetchPartnerOnBoardDataRequest
import com.blaze.data.onboarding.model.req.PartnerOnBoardRequest
import com.blaze.data.onboarding.model.res.FetchPartnerOnBoardDataResponse
import com.blaze.data.onboarding.model.res.PartnerOnBoardResponse
import com.google.firebase.auth.FirebaseAuth
import com.velox.lazeir.utils.handler.NetworkResource
import kotlinx.coroutines.flow.Flow



interface OnBoardingRepo {

    fun getAuth(): FirebaseAuth
    suspend fun fetchUserOnBoardData(
        body: FetchPartnerOnBoardDataRequest,
    ): Flow<NetworkResource<FetchPartnerOnBoardDataResponse>>

    fun uploadImage(   userId: String,
                               imageId: String,
                               imageByteArray: ByteArray,
                               onFailure: (String?) -> Unit,
                               onSuccess: (String?) -> Unit)

    suspend fun onBoard(body:PartnerOnBoardRequest):Flow<NetworkResource<PartnerOnBoardResponse>>
}