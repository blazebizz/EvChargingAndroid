package com.blaze.feature.dashboard.utils

import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(
        isMyLocationEnabled = true
    ), val isFalloutMap: Boolean = true,
    val parkingSpots: List<ParkingSpot> = emptyList()

)
