package com.blaze.feature.dashboard.screen.sidenav

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SideNavigationScreenViewModel @Inject constructor(private val auth: FirebaseAuth) :
    ViewModel() {
    val firebaseAuth = auth
}