package com.blaze.data.onboarding.apiservice

import com.blaze.data.onboarding.model.req.FetchPartnerOnBoardDataRequest
import com.blaze.data.onboarding.model.req.PartnerOnBoardRequest
import com.blaze.data.onboarding.model.res.FetchPartnerOnBoardDataResponse
import com.blaze.data.onboarding.model.res.PartnerOnBoardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OnBoardingApiService {

    @POST("/partner/fetch-data/")
    fun fetchUserOnBoardData(
        @Body body: FetchPartnerOnBoardDataRequest
    ): Response<FetchPartnerOnBoardDataResponse>

    @POST("/partner/onboard")
    fun onBoardUser(
        @Body body: PartnerOnBoardRequest
    ): Response<PartnerOnBoardResponse>


}