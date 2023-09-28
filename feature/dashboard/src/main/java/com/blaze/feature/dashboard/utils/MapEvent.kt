package com.blaze.feature.dashboard.utils

import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleFallOutMap : MapEvent()
    data class OnMapLongClick(val latlng: LatLng) : MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot) : MapEvent()

}
