package com.blaze.feature.startup.screen.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(auth: FirebaseAuth) : ViewModel() {
    val firebaseAuth = auth
}