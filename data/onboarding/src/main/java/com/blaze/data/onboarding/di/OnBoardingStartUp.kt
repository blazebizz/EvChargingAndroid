package com.blaze.data.onboarding.di

import com.blaze.data.onboarding.apiservice.OnBoardingApiService
import com.blaze.data.onboarding.repositories.OnBoardingRepo
import com.blaze.data.onboarding.repositories.OnBoardingRepoImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardingStartUp {

    @Provides
    @Singleton
    fun provideOnBoardingRepository(
        auth: FirebaseAuth,
        apiService: OnBoardingApiService
    ): OnBoardingRepo {
        return OnBoardingRepoImpl(
            auth = auth,
            apiService = apiService
        )
    }
}