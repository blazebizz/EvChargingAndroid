package com.blaze.data.startup.di

import com.blaze.data.startup.apiservice.StartUpApiService
import com.blaze.data.startup.repositories.StartUpRepo
import com.blaze.data.startup.repositories.StartUpRepoImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StartUpModule {

    @Provides
    @Singleton
    fun provideFirebaseStartUpRepository(auth: FirebaseAuth, apiService: StartUpApiService):StartUpRepo{
        return StartUpRepoImpl(auth,apiService)
    }
}