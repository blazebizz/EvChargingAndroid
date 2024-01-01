package com.blaze.data.startup.apiservice

import com.blaze.data.startup.model.req.CreateUserRequest
import com.blaze.data.startup.model.req.GenerateTokenRequest
import com.blaze.data.startup.model.res.CreateUserResponse
import com.blaze.data.startup.model.res.GenerateTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StartUpApiService {
    @POST("api/v1/generate_token")
    suspend fun generateToken(
        @Body body: GenerateTokenRequest
    ): Response<GenerateTokenResponse>

    @POST("api/v1/createuser")
    suspend fun createUser(
        @Body body: CreateUserRequest
    ): Response<CreateUserResponse>


}