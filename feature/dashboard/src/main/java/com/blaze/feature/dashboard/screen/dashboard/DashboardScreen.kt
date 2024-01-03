package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.TopBar
import com.blaze.core.ui.components.bounceClick
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.util.logi
import com.blaze.core.utils.util.requestLocationEnable
import com.blaze.feature.dashboard.screen.bottomsheet.BottomSheetContent
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DashboardScreen(
    navController: NavController, coreVM: CoreViewModel, dashboardViewModel: DashboardViewModel
) {
    val activity = LocalContext.current as Activity
    val modalSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val activateBottomSheet = remember { mutableStateOf(false) }


    LaunchedEffect(key1 = coreVM.isGpsEnabled.value) {
        if (!coreVM.isGpsEnabled.value) {
            requestLocationEnable(activity)
            delay(10000)
        }
    }


    LaunchedEffect(key1 = Unit) {
        if (!coreVM.isGpsEnabled.value) {
            requestLocationEnable(activity)
        } else {
            if (dashboardViewModel.onFirstLoad.value) {
                coreVM.mapLocation.value = coreVM.currentLocation.value
                dashboardViewModel.onFirstLoad.value = !dashboardViewModel.onFirstLoad.value
            }
        }
    }


    BackHandler {
        activity.finishAffinity()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
//            containerColor = Color.Transparent,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)) {


            Box(
                Modifier
                    .padding()
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                DashboardMapContent(Modifier, dashboardViewModel, coreVM, onMarkerClick = {
                    activateBottomSheet.value = true
                })

                TopBar(text = coreVM.searchText.value,
                    headerIcon = R.drawable.logo_square,
                    trailingIcon = R.drawable.baseline_search_24,
                    headerOnClick = {
                        navController.navigate(DashboardRoute.SideNavigationScreen.route)
                    },
                    textOnClick = {
                        navController.navigate(DashboardRoute.SearchScreen.route)
                    })

                Icon(painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null,
                    modifier = Modifier
                        .bounceClick {
                            logi("mapLocation to currentLocation")
                            if (coreVM.isGpsEnabled.value) {
                                coreVM.mapLocation.value = coreVM.currentLocation.value
                            } else {
                                requestLocationEnable(activity)
                            }
                        }
                        .align(Alignment.BottomEnd)
                        .padding(end = 24.dp, bottom = 24.dp)
                        .background(MaterialTheme.colorScheme.background, CircleShape)
                        .padding(12.dp))

            }
        }



        if (activateBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    activateBottomSheet.value = false
                }, sheetState = modalSheetState
            ) {
                BottomSheetContent(activateBottomSheet, modalSheetState, coreVM, navController)
            }

        }
    }
}


@Composable
fun Wait() {

}

@Composable
fun GpsDialog(state: MutableState<Boolean>, coreVM: CoreViewModel) {
    if (state.value) Dialog(onDismissRequest = {

    }) {
//        val context = LocalContext.current
//        val openLocationSettings =
//            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                state.value = !state.value
//                if (coreVM.isGpsEnabled.value) {
//                    mainScope.launch {
//                        Toast.makeText(context, "Thank You, Please Enjoy!", Toast.LENGTH_SHORT).show()
//                        delay(2000)
//                        coreVM.mapLocation.value = coreVM.currentLocation.value
//                    }
//                }
//            }

        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val errorLottie =
                rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.location))
            val progress by animateLottieCompositionAsState(
                composition = errorLottie.value, restartOnPlay = true, iterations = Int.MAX_VALUE
            )

            LottieAnimation(
                composition = errorLottie.value,
                progress = progress,
                modifier = Modifier.size(300.dp)
            )

            Text(text = "Please Enable GPS for better experience !")
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {

                Spacer(modifier = Modifier.weight(1f))
                Text(text = "OK", modifier = Modifier
                    .clickable {
//                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                        openLocationSettings.launch(intent)
                        coreVM.openLocationSetting()
                    }
                    .padding(10.dp)
                    .background(
                        MaterialTheme.colorScheme.onBackground, RoundedCornerShape(16.dp)
                    )
                    .padding(15.dp, 10.dp), color = MaterialTheme.colorScheme.background)
//                    Spacer(modifier = Modifier.weight(1f))

            }
        }
    }
}


fun Int.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    )
}