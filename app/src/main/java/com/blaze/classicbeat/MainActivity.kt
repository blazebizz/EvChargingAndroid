package com.blaze.classicbeat

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.blaze.classicbeat.navigation.SetupNavGraph
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.ui.InitSubUiComponents
import com.blaze.core.ui.ui.theme.ClassicBeatTheme
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.core.utils.util.RationaleState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val permissionState = rememberMultiplePermissionsState(
                listOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                ),
            )

            // Keeps track of the rationale dialog state, needed when the user requires further rationale
            var rationaleState = remember {
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

            MainActivityScreen()


        }
    }
}

@Composable
fun MainActivityScreen() {
    ClassicBeatTheme {
        // A surface container using the 'background' color from the theme
        val coreUi = hiltViewModel<CoreUiViewModel>()
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {


            val navGraphController = rememberNavController()
            SetupNavGraph(
                startDestination = StartUpRoute.SplashScreen.route,
                navController = navGraphController,
                coreUi = coreUi
            )
        }

        InitSubUiComponents(coreUi)
    }
}

