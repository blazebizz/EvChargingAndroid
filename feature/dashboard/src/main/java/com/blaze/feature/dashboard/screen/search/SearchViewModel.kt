package com.blaze.feature.dashboard.screen.search

import android.annotation.SuppressLint
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blaze.core.ui.data.model.response.AutocompleteResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LocationState {
    object NoPermission : LocationState()
    object LocationDisabled : LocationState()
    object LocationLoading : LocationState()
    data class LocationAvailable(val cameraLatLang: LatLng) : LocationState()
    object Error : LocationState()
}


@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var placesClient: PlacesClient
    lateinit var geoCoder: Geocoder

    var locationState by mutableStateOf<LocationState>(LocationState.NoPermission)

    val locationAutofill = SnapshotStateList<AutocompleteResult>()


    private var job: Job? = null


    fun searchPlaces(query: String) {
        job?.cancel()
        locationAutofill.clear()
        job = viewModelScope.launch {
            val request = FindAutocompletePredictionsRequest.builder().setQuery(query).build()

            placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
                Log.e("searchPlaces", "searchPlaces: $response")
                locationAutofill += response.autocompletePredictions.map {
                    AutocompleteResult(it.getFullText(null).toString(), it.placeId)
                }
            }.addOnFailureListener {
                it.printStackTrace()
                println(it.cause)
                println(it.message)
            }
        }
    }

    fun getCoordinates(result: AutocompleteResult,onSuccess: (latLng: LatLng) -> Unit) {
        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(result.placeId, placeFields)
        placesClient.fetchPlace(request).addOnSuccessListener {
            if (it != null) {
                onSuccess( it.place.latLng!!)
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }
}

