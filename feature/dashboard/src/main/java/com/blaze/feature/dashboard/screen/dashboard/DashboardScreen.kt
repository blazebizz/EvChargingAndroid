package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.DefaultShape
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.bounceClick
import com.blaze.core.ui.components.pressClick
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.util.logi
import com.blaze.core.utils.util.requestLocationEnable
import com.blaze.feature.dashboard.screen.bottomsheet.BottomSheetContent
import kotlinx.coroutines.delay

@Composable
@Preview
fun DashboardScreenPreview() {
    val navController = rememberNavController()
    val coreVM = hiltViewModel<CoreViewModel>()
    val viewModel = hiltViewModel<DashboardViewModel>()
    DashboardScreen(navController, coreVM, viewModel)
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DashboardScreen(
    navController: NavController, coreVM: CoreViewModel, viewModel: DashboardViewModel
) {
    val activity = LocalContext.current as Activity
    val modalSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val activateBottomSheet = remember { mutableStateOf(false) }

    val isKeyboardShowing = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    LaunchedEffect(key1 = coreVM.isGpsEnabled.value) {
        if (!coreVM.isGpsEnabled.value) {
            requestLocationEnable(activity) // launch GPS dialog
        }
    }


    LaunchedEffect(key1 = Unit) {
        if (coreVM.isGpsEnabled.value) {
            if (viewModel.onFirstLoad.value) {
                delay(3000)
                coreVM.mapLocation.value = coreVM.currentLocation.value
                viewModel.onFirstLoad.value = !viewModel.onFirstLoad.value
            }
        }
    }


    BackHandler {
        activity.finishAffinity()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {

        //region map section

        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Box(
                Modifier
                    .padding()
                    .fillMaxSize()
            ) {

                MapContent(Modifier.pointerInput(Unit) {

                }, viewModel, coreVM, onMarkerClick = {
                    activateBottomSheet.value = true
                })

                Image(painter = painterResource(R.drawable.logo_square),
                    contentDescription = null,
                    Modifier
                        .padding(18.dp)
                        .size(50.dp)
                        .bounceClick {
                            navController.navigate(DashboardRoute.SideNavigationScreen.route)
                        }
                        .clip(CircleShape))

//                TopBar(text = coreVM.searchText.value,
//                    headerIcon = R.drawable.logo_square,
//                    trailingIcon = R.drawable.baseline_search_24,
//                    headerOnClick = {
//                        navController.navigate(DashboardRoute.SideNavigationScreen.route)
//                    },
//                    textOnClick = {
//                        navController.navigate(DashboardRoute.SearchScreen.route)
//                    })
                val offset by animateDpAsState(
                    if (isKeyboardShowing) (-250).dp else 0.dp,
                    animationSpec = tween(durationMillis = 300), label = ""
                )
                Column(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .wrapContentHeight()
                        .background(Color.Transparent)
                        .pointerInput(Unit) {
                            detectTapGestures {

                            }
                        }.offset(x= 0.dp,y = offset )
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_gps_fixed_24),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = null,
                        modifier = Modifier
                            .bounceClick {
                                logi("mapLocation to currentLocation")
                                if (coreVM.isGpsEnabled.value) {
                                    coreVM.mapLocation.value = coreVM.currentLocation.value
                                    viewModel.userLocationSelected.value = true
                                } else {
                                    requestLocationEnable(activity)
                                }
                            }
                            .align(Alignment.End)
                            .padding(bottom = 16.dp)
                            .background(MaterialTheme.colorScheme.background, CircleShape)
                            .padding(12.dp))

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(
                                MaterialTheme.colorScheme.background,
                                RoundedCornerShape(16.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            Modifier.padding(bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Select Location",
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            if (!viewModel.selectLocationFromMap.value) Text(
                                text = "Select From Map",
                                modifier = Modifier
                                    .bounceClick {
                                        viewModel.selectLocationFromMap.value = true
                                    }
                                    .weight(1f),
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.End,
                                fontSize = 12.sp
                            )
                        }

                        Row(
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    RoundedCornerShape(10.dp)
                                )
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                    RoundedCornerShape(10.dp)
                                )
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            BasicTextField(
                                value = coreVM.searchText.value,
                                onValueChange = { text ->
                                    if (!viewModel.selectLocationFromMap.value) {
                                        coreVM.searchText.value = text
                                        viewModel.searchPlaces(text)
                                    }
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp),
                                textStyle = TextStyle(
                                    color = MaterialTheme.colorScheme.onBackground,
                                ),
                                cursorBrush = Brush.linearGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.onBackground,
                                        MaterialTheme.colorScheme.onBackground
                                    )
                                )
                            )
                            Spacer(Modifier.width(12.dp))

                            val iconPadding =
                                if (viewModel.selectLocationFromMap.value) PaddingValues(
                                    start = 14.dp, end = 14.dp
                                ) else PaddingValues(0.dp)

                            Icon(painter = painterResource(if (viewModel.selectLocationFromMap.value) R.drawable.arrow_right_24 else R.drawable.baseline_close_24),
                                contentDescription = "search button",
                                Modifier
                                    .fillMaxHeight()
                                    .bounceClick {
                                        if (viewModel.selectLocationFromMap.value) {
                                            viewModel.selectLocationFromMap.value = false
                                            //todo getMarker Api Calls
                                        } else {
                                            coreVM.searchText.value = ""
                                            viewModel.locationAutofill.clear()
                                            viewModel.userLocationSelected.value = false
                                        }
                                    }

                                    .background(
                                        color = if (viewModel.selectLocationFromMap.value) MaterialTheme.colorScheme.primary else Color.Transparent,
                                        shape = if (viewModel.selectLocationFromMap.value) RoundedCornerShape(
                                            12.dp
                                        ) else RectangleShape
                                    )
                                    .padding(iconPadding),
                                tint = if (viewModel.selectLocationFromMap.value) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground)

                            Spacer(Modifier.width(12.dp))
                        }

                        if (viewModel.locationAutofill.isNotEmpty()) {
                            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                items(items = viewModel.locationAutofill) {
                                    Row(
                                        Modifier
                                            .padding(start = 16.dp, end = 16.dp, top = 5.dp)
                                            .defaultMinSize(minHeight = 50.dp)
                                            .background(
                                                MaterialTheme.colorScheme.background, DefaultShape
                                            )
                                            .pressClick {
                                                coreVM.searchText.value = it.address
                                                viewModel.getCoordinates(it) { latLng ->
                                                    logi("SearchScreen: lat: ${latLng.latitude}  lng: ${latLng.longitude}")
                                                    coreVM.mapLocation.value = latLng
                                                    viewModel.userLocationSelected.value = true
                                                    viewModel.locationAutofill.clear()
                                                }
                                            }
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painterResource(id = R.drawable.location_pin3),
                                            contentDescription = null,
                                            Modifier.size(15.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = it.address,
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        } else {
                            if (!viewModel.userLocationSelected.value) {
                                Text(
                                    "Explore",
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 5.dp),
                                    fontSize = 14.sp
                                )

                                Row(
                                    Modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth(),
                                ) {
                                    VicheleItem(img = R.drawable.scooty_img, title = "Scooter") {
                                        coreVM.toast("scooter")
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))
                                    VicheleItem(img = R.drawable.bike_img, title = "Bike") {
                                        coreVM.toast("bike")
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))
                                    VicheleItem(img = R.drawable.car_img, title = "Car") {
                                        coreVM.toast("Car")
                                    }
                                }

                            } else {
                                if (!viewModel.selectLocationFromMap.value) {
                                    Column(Modifier.fillMaxSize()) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(text = "Search Filter",
                                            modifier = Modifier
                                                .pressClick {
                                                    coreVM.toast("filter search by preference")
                                                }
                                                .align(Alignment.End),
//                                        .padding(bottom = 10.dp)
                                            fontSize = 12.sp)
                                        Button(
                                            text = "Search & Book Instantly",
                                            modifier = Modifier.fillMaxWidth()
                                        ) {

                                        }
                                    }
                                } else {
                                    //thinking what to do
                                }
                            }

                        }
                    }
                }
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

        //end region
    }
}


@Composable
fun GpsDialog(state: MutableState<Boolean>, coreVM: CoreViewModel) {
    if (state.value) Dialog(onDismissRequest = {

    }) {

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

@Composable
fun VicheleItem(img: Int, title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.pressClick {
            onClick()
        }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(10.dp)
                )
                .size(70.dp, 50.dp)
                .padding(5.dp)

        )
        Text(text = title, fontWeight = FontWeight.SemiBold)
    }
}
