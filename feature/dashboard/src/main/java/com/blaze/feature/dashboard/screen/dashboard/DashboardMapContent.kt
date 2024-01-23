package com.blaze.feature.dashboard.screen.dashboard

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.utils.util.logi
import com.blaze.core.utils.util.mainScope
import com.blaze.feature.dashboard.utils.MAP_ZOOM
import com.blaze.feature.dashboard.utils.ParkingSpot
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.PointOfInterest
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch


@Composable
fun MapContent(
    modifier: Modifier,
    viewModel: DashboardViewModel,
    coreVM: CoreViewModel,
    onPOIClick: (PointOfInterest) -> Unit = {},
    onMarkerClick: (Marker) -> Unit,
) {

    Box(modifier = modifier.fillMaxSize()) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(coreVM.currentLocation.value, MAP_ZOOM)
        }
        val context = LocalContext.current
        val uiSettings = remember {
            MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = false,
                compassEnabled = false,
                tiltGesturesEnabled = false,
                mapToolbarEnabled = false,
                zoomGesturesEnabled = false,
                scrollGesturesEnabled = true,
            )
        }


        LaunchedEffect(key1 = coreVM.getMapLocation()) {
            logi("mapLocation changed: lat- ${coreVM.getMapLocation().latitude}, lng- ${coreVM.getMapLocation().longitude} ")
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(coreVM.getMapLocation(), MAP_ZOOM)
        }

        LaunchedEffect(cameraPositionState.isMoving) {
            if (!cameraPositionState.isMoving && viewModel.selectLocationFromMap.value) {
                viewModel.getAddress(context, cameraPositionState.position.target) {
                    logi(it)

                    if (viewModel.onFirstLoad.value) {
                        if (coreVM.isGpsEnabled.value) {
                            coreVM.setMapLocation(coreVM.currentLocation.value)
                        }
                        viewModel.onFirstLoad.value = !viewModel.onFirstLoad.value
                    } else {
                        coreVM.searchText.value = it
                        viewModel.userLocationSelected.value = true
                    }


                }
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState, //upon getting the current location set to this
            onMapLoaded = {},
            onMapLongClick = {
//                viewModel.onEvent(MapEvent.OnMapLongClick(it))
                Toast.makeText(context, "${it.latitude}   ${it.longitude}", Toast.LENGTH_SHORT)
                    .show()
                viewModel.state.parkingSpots.add(ParkingSpot(it))
            },
            onPOIClick = {
                onPOIClick(it)
            },
            onMapClick = {
                mainScope.launch {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLng(it))
                }
            }
        ) {
            viewModel.state.parkingSpots.forEach { spot ->
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
                        onMarkerClick(it)
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )
            }
        }


//        if (viewModel.selectLocationFromMap.value){

        Image(
            painter = painterResource(id = com.blaze.core.ui.R.drawable.location_pin2),
            contentDescription = "Parking",
            modifier = Modifier
                .size(26.dp)
                .align(Alignment.Center)
                .offset(y = if (cameraPositionState.isMoving) (-25).dp else (-10).dp)
        )

        Spacer(
            modifier = Modifier
                .offset(y = (5).dp)
                .height(3.dp)
                .width(15.dp)
                .background(Color.Gray, RoundedCornerShape(2.dp))
                .align(Alignment.Center)
        )
//        }

    }
}


