package com.blaze.data.startup.di

import com.blaze.core.utils.util.BASE_URL_V1
import com.blaze.core.utils.util.BASE_URL_V2
import com.blaze.data.startup.apiservice.StartUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object StartUpRetrofitBuilder {

    @Provides
    @Singleton
    fun provideStartRetrofit (okHttpClient: OkHttpClient): StartUpApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_V2)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(StartUpApiService::class.java)
    }
}