package com.blaze.data.onboarding.repositories

import android.net.Uri
import com.blaze.data.onboarding.model.req.FetchPartnerOnBoardDataRequest
import com.blaze.data.onboarding.model.res.FetchPartnerOnBoardDataResponse
import com.velox.lazeir.utils.handler.NetworkResource
import kotlinx.coroutines.flow.Flow



interface OnBoardingRepo {
    suspend fun fetchUserOnBoardData(
        body: FetchPartnerOnBoardDataRequest,
    ): Flow<NetworkResource<FetchPartnerOnBoardDataResponse>>

    fun uploadImage(   userId: String,
                               imageId: String,
                               imageUri: Uri,
                               onFailure: (String?) -> Unit,
                               onSuccess: (String?) -> Unit)
}