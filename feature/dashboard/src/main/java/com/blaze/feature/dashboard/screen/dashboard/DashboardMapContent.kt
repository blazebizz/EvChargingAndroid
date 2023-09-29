package com.blaze.feature.dashboard.screen.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings


@Composable
fun DashboardMapContent(modifier: Modifier, dashboardViewModel: DashboardViewModel,) {
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
        GoogleMap(modifier = Modifier.fillMaxSize(),
            properties = dashboardViewModel.state.properties,
            uiSettings = uiSettings,
//            cameraPositionState = CameraPositionState(CameraPosition()), //upon getting the current location set to this
            onMapLongClick = {
//                viewModel.onEvent(MapEvent.OnMapLongClick(it))
                Toast.makeText(context, "${it.latitude}   ${it.longitude}", Toast.LENGTH_SHORT).show()
            }) {
//            viewModel.state.parkingSpots.forEach { spot ->
//                Marker(position = LatLng(spot.lat, spot.lng),
//                    title = "Parking Sport ${spot.lat} ${spot.lng}",
//                    snippet = "Long click to delete",
//                    onInfoWindowLongClick = {
////                        viewModel.onEvent(
////                            MapEvent.OnInfoWindowLongClick(spot)
////                        )
//                    },
//                    onClick = {
////                        it.showInfoWindow()
//                        true
//                    },
//                    icon = BitmapDescriptorFactory.defaultMarker(
//                        BitmapDescriptorFactory.HUE_ORANGE
//                    )
//                )
//            }
        }


    }
}
