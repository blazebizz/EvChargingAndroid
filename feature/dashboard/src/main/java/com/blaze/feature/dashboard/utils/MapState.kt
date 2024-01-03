package com.blaze.feature.dashboard.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.maps.android.compose.MapProperties

const val MAP_ZOOM = 14f
data class MapState(
    val properties: MapProperties = MapProperties(
        isMyLocationEnabled = true,
        maxZoomPreference = MAP_ZOOM,
        minZoomPreference = MAP_ZOOM,
    ), val isFalloutMap: Boolean = true,

    val parkingSpots: SnapshotStateList<ParkingSpot> = mutableStateListOf()

)
