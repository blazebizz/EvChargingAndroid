package com.blaze.data.onboarding.di

import com.blaze.core.utils.BuildConfig.BASE_URL
import com.blaze.data.onboarding.apiservice.OnBoardingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardingRetrofitBuilder {
    @Provides
    @Singleton
    fun provideStartUpRetrofitBuilder(client: OkHttpClient): OnBoardingApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create()).client(client).build()
            .create(OnBoardingApiService::class.java)
    }
}

