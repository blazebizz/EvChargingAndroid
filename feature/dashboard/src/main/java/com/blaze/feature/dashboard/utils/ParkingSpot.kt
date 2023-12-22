package com.blaze.feature.dashboard.utils

import com.google.android.gms.maps.model.LatLng

data class ParkingSpot(
    val LatLng: LatLng,
    val id: Int? = null
)
