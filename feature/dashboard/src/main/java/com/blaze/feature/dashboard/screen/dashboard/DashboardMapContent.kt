package com.blaze.feature.dashboard.screen.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.utils.util.logi
import com.blaze.feature.dashboard.utils.MAP_ZOOM
import com.blaze.feature.dashboard.utils.ParkingSpot
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PointOfInterest
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun DashboardMapContent(
    modifier: Modifier,
    dashboardViewModel: DashboardViewModel,
    coreVM: CoreViewModel,
    onPOIClick: (PointOfInterest) -> Unit = {},
    onMarkerClick: (Marker) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        val context = LocalContext.current
        val uiSettings = remember {
            MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = false,
                compassEnabled = false
            )
        }


        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(coreVM.currentLocation.value, MAP_ZOOM)
        }


        LaunchedEffect(key1 = coreVM.mapLocation.value) {
            logi("mapLocation changed: lat- ${coreVM.mapLocation.value.latitude}, lng- ${coreVM.mapLocation.value.longitude} ")
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(coreVM.mapLocation.value, MAP_ZOOM)
        }



        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = dashboardViewModel.state.properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState, //upon getting the current location set to this
            onMapLoaded = {

            },
            onMapLongClick = {

//                viewModel.onEvent(MapEvent.OnMapLongClick(it))
                Toast.makeText(context, "${it.latitude}   ${it.longitude}", Toast.LENGTH_SHORT)
                    .show()
                dashboardViewModel.state.parkingSpots.add(ParkingSpot(it))

            },
            onPOIClick = {
                onPOIClick(it)
            }) {
            dashboardViewModel.state.parkingSpots.forEach { spot ->
                Marker(
                    position = spot.LatLng,
                    title = "Parking Sport ${spot.LatLng.latitude} ${spot.LatLng.longitude}",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
//                        viewModel.onEvent(
//                            MapEvent.OnInfoWindowLongClick(spot)
//                        )
                    },
                    onClick = {
//                        it.showInfoWindow()
                        onMarkerClick(it)
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )
            }
        }


    }
}
