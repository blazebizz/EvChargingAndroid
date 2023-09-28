package com.blaze.core.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blaze.core.utils.util.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor() : ViewModel() {


    val currentUserNumber: MutableState<String> = mutableStateOf("+91-XXXXXXXXXX")
    val searchText: MutableState<String> = mutableStateOf("")

    val loading: MutableState<Boolean> = mutableStateOf(false)
    val isInternetAvailable: MutableState<Boolean> = mutableStateOf(true)
    internal val snackbarValue: MutableState<Pair<Boolean, String>> =
        mutableStateOf(Pair(false, ""))
    internal val toast: MutableState<String> = mutableStateOf("")

    fun snackbar(message: String) {
        viewModelScope.launch {
            snackbarValue.value = Pair(true, message)
            delay(3000)
            snackbarValue.value = Pair(false, message)

        }
    }

    fun toast(message: String) {
        viewModelScope.launch {
            toast.value = message
        }
    }

}