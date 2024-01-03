package com.blaze.feature.dashboard.screen.dashboard

import android.content.Context
import android.content.IntentSender
import android.location.Geocoder
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blaze.core.ui.data.model.response.AutocompleteResult
import com.blaze.feature.dashboard.utils.MapState
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor():ViewModel() {
    var state by mutableStateOf(MapState())

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var placesClient: PlacesClient
    lateinit var geoCoder: Geocoder
    val onFirstLoad = mutableStateOf(true)

    val locationAutofill = mutableStateListOf<AutocompleteResult>()

    private var job: Job? = null

    fun searchPlaces(query: String) {
        job?.cancel()
        locationAutofill.clear()
        job = viewModelScope.launch {
            val request = FindAutocompletePredictionsRequest
                .builder()
                .setQuery(query)
                .build()

            placesClient
                .findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    locationAutofill += response.autocompletePredictions.map {
                        AutocompleteResult(
                            it.getFullText(null).toString(),
                            it.placeId
                        )
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    println(it.cause)
                    println(it.message)
                }
        }
    }

    //Not necessarily in VM, you can also create it in your UI.
    fun enableLocationRequest(
        context: Context,
        makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit//Lambda to call when locations are off.
    ) {
        val locationRequest = LocationRequest.Builder(//Create a location request object
            Priority.PRIORITY_HIGH_ACCURACY,//Self explanatory
            10000//Interval -> shorter the interval more frequent location updates
        ).build()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())//Checksettings with building a request
        task.addOnSuccessListener { locationSettingsResponse ->
            Log.d(
                "Location",
                "enableLocationRequest: LocationService Already Enabled"
            )
        }
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution).build()//Create the request prompt
                    makeRequest(intentSenderRequest)//Make the request from UI
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }
}

