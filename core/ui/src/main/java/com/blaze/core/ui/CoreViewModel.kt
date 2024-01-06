package com.blaze.core.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blaze.core.utils.data.repo.LocationRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
) : ViewModel() {


    var currentLocation: MutableState<LatLng> = mutableStateOf(LatLng(23.0, 79.0))//44.837789, long- -0.57918
    var mapLocation: MutableState<LatLng> = mutableStateOf(currentLocation.value)


    val currentUserNumber: MutableState<String> = mutableStateOf("+91-XXXXXXXXXX")
    val currentUserName: MutableState<String> = mutableStateOf("Guest User")
    val currentUserType: MutableState<String> = mutableStateOf("Customer")
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

    // region location
    val isGpsEnabled = locationRepository.isGpsEnabled
    val gpsHardwareEnabled = locationRepository.gpsHardwareEnabled
    fun openLocationSetting() = locationRepository.openLocationSetting()
    fun registerGpsStateReceiver() = locationRepository.registerGpsStateReceiver()
    fun unregisterGpsStateReceiver() = locationRepository.unregisterGpsStateReceiver()

    //getting only single location object
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentLocation(
        latLng: MutableState<LatLng>
    ) = locationRepository.getCurrentLocation(latLng)


    //endregion

}