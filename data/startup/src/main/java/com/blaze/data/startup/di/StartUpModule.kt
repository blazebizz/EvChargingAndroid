package com.blaze.data.startup.di

import com.blaze.data.startup.repositories.StartUpRepo
import com.blaze.data.startup.repositories.StartUpRepoImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
    fun provideFirebaseStartUpRepository():StartUpRepo{
        val auth: FirebaseAuth = Firebase.auth
        return StartUpRepoImpl(auth = auth)
    }
}