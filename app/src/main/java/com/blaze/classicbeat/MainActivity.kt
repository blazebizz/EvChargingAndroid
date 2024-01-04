package com.blaze.classicbeat

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.blaze.classicbeat.navigation.SetupNavGraph
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.InitSubUiComponents
import com.blaze.core.ui.components.location.LocationUpdatesEffect
import com.blaze.core.ui.ui.theme.ClassicBeatTheme
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.core.utils.observer.DELAY_MILLIS
import com.blaze.core.utils.util.RationaleState
import com.blaze.feature.onboarding.screen.OnBoardingViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.velox.lazeir.utils.InternetConnectivityListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
        // Initialize the SDK
//        crashHandler()
        setContent {
            val coreViewModel = hiltViewModel<CoreViewModel>()

            LaunchedEffect(Unit) {
                coreViewModel.registerGpsStateReceiver()
            }

            BackHandler {
                coreViewModel.unregisterGpsStateReceiver()
            }

            ClassicBeatTheme {

                val gettingContinuesLocation = remember { mutableStateOf(true) }
                // The location request that defines the location updates
                var locationRequest by remember {
                    mutableStateOf<LocationRequest?>(null)
                }

                val permissionState = rememberMultiplePermissionsState(
                    listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ),
                )

                // Keeps track of the rationale dialog state, needed when the user requires further rationale
                val rationaleState = remember {
                    mutableStateOf<RationaleState?>(null)
                }

                fun askForPermission() {
                    if (permissionState.shouldShowRationale) {
                        rationaleState.value = RationaleState(
                            "Request Precise Location",
                            "In order to use this feature please grant access by accepting " + "the location permission dialog." + "\n\nWould you like to continue?",
                        ) { proceed ->
                            if (proceed) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                            rationaleState.value = null
                        }
                    } else {
                        permissionState.launchMultiplePermissionRequest()
                    }
                }

                LaunchedEffect(key1 = Unit) {
                    if (!permissionState.allPermissionsGranted) {
                        askForPermission()
                    }
                }

                // Only register the location updates effect when we have a request
                if (locationRequest != null) {
                    LocationUpdatesEffect(locationRequest!!) { result ->
                        // For each result update the text
                        for (currentLocation in result.locations) {
                            coreViewModel.currentLocation.value =
                                LatLng(currentLocation.latitude, currentLocation.longitude)
                        }
                    }
                }


                LaunchedEffect(key1 = gettingContinuesLocation.value) {
                    locationRequest = if (gettingContinuesLocation.value) {
                        // Define the accuracy based on your needs and granted permissions
                        val priority =
                            Priority.PRIORITY_BALANCED_POWER_ACCURACY //Priority.PRIORITY_HIGH_ACCURACY
                        val requester = LocationRequest.Builder(priority, DELAY_MILLIS)
                        requester.setMinUpdateIntervalMillis(2 * DELAY_MILLIS)
                        requester.build()
                    } else {
                        null
                    }
                }


                LaunchedEffect(key1 = coreViewModel.gpsHardwareEnabled) {
                    coreViewModel.isGpsEnabled.value = coreViewModel.gpsHardwareEnabled
                }

                LaunchedEffect(key1 = coreViewModel.isGpsEnabled.value) {
                    gettingContinuesLocation.value = coreViewModel.isGpsEnabled.value
                }

                MainActivityScreen(lifecycleScope, coreViewModel)
            }
        }
    }
}

@Composable
fun MainActivityScreen(lifecycleScope: LifecycleCoroutineScope, coreViewModel: CoreViewModel) {

    ClassicBeatTheme {
        // A surface container using the 'background' color from the theme
        val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
        val isInternetAvailable = coreViewModel.isInternetAvailable

        val context = LocalContext.current

        InternetConnectivityListener(lifecycleScope = lifecycleScope,
            onAvailable = { isInternetAvailable.value = true },
            onUnAvailable = { isInternetAvailable.value = false },
            onLosing = { isInternetAvailable.value = false },
            onLost = { isInternetAvailable.value = false })

        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val navGraphController = rememberNavController()
            SetupNavGraph(
//                startDestination = StartUpRoute.SplashScreen.route,
                startDestination = DashboardRoute.DashboardScreen.route,
                navController = navGraphController,
                coreVm = coreViewModel,
                onBoardingViewModel = onBoardingViewModel
            )

            //region internet connectivity dialog
            if (!coreViewModel.isInternetAvailable.value) {
                Dialog(onDismissRequest = {
                    coreViewModel.toast("Please Connect To Internet And Try Again")
                }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = White, RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Row(Modifier.height(40.dp)) {
                            Text(text = "ZAPE", fontWeight = FontWeight.Black)
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(id = com.blaze.core.ui.R.drawable.logo_square),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(30.dp)
                                    .clip(CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Internet Connection Lost,\nPlease Connect And Try Again!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            //endregion
        }
        InitSubUiComponents(coreViewModel)
    }
}

//In activity

