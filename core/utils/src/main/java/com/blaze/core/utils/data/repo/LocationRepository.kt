package com.blaze.core.utils.data.repo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.blaze.core.utils.observer.GpsConnectivityObserver
import com.blaze.core.utils.util.hasLocationPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val locationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = mutableStateOf(false)
    val gpsHardwareEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    private val gpsStateReceiver = GpsConnectivityObserver(isGpsEnabled)
    private val isLocationReceiverRegistered = mutableStateOf(false)


    fun openLocationSetting() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun registerGpsStateReceiver() {
        val intentFilter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        context.registerReceiver(gpsStateReceiver, intentFilter)
        isLocationReceiverRegistered.value = true

    }

    fun unregisterGpsStateReceiver() {
        if (isLocationReceiverRegistered.value) {
            context.unregisterReceiver(gpsStateReceiver)
            isLocationReceiverRegistered.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        latLng: MutableState<LatLng>
    ) {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            val priority = Priority.PRIORITY_HIGH_ACCURACY
            if (context.hasLocationPermission()) {
                val result = locationClient.getCurrentLocation(
                    priority,
                    CancellationTokenSource().token,
                ).await()
                result?.let { fetchedLocation ->
                    latLng.value = LatLng(fetchedLocation.latitude,fetchedLocation.longitude)
                }
            }
        }
    }
}