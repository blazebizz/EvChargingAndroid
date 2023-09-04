package com.blaze.feature.onboarding.screen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.data.onboarding.repositories.OnBoardingRepo
import com.blaze.data.onboarding.repositories.OnBoardingRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val repo: OnBoardingRepo):ViewModel() {


    //page1
    val selected2Wheeler = mutableStateOf(false)
    val selected4Wheeler = mutableStateOf(false)

    //page2
    val fullName= mutableStateOf("")
    val mobile = mutableStateOf("")
    val addressL1 = mutableStateOf("")
    val addressL2 = mutableStateOf("")
    val state = mutableStateOf("")
    val pincode = mutableStateOf("")

    //page3
    val aadharNumber = mutableStateOf("")
    val panNumber = mutableStateOf("")
    val electricProvide = mutableStateOf("")
    val electricBillNumber = mutableStateOf("")

    //page4
    val aadharUri = mutableStateOf<Uri?>(null)
    val panUri = mutableStateOf<Uri?>(null)
    val electricBillUri = mutableStateOf<Uri?>(null)
    val otherUri = mutableStateOf<Uri?>(null)

    //page5
    val accNameHolder = mutableStateOf("")
    val bankName = mutableStateOf("")
    val accNumber = mutableStateOf("")
    val accConfirmNumber = mutableStateOf("")
    val ifscCode = mutableStateOf("")



    fun fetchOnBoardUserData(){

    }

    fun onBoardUser() {

    }
}