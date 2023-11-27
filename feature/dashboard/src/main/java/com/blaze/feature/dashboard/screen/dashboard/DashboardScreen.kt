package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.TopBar
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.logi
import com.blaze.feature.dashboard.screen.bottomsheet.BottomSheetContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    coreVM: CoreViewModel,
    dashboardViewModel: DashboardViewModel
) {
    val activity = LocalContext.current as Activity
    val sheetState = rememberBottomSheetScaffoldState()
    val activateBottomSheet = rememberSaveable { mutableStateOf(false) }
    val showGpsDialog = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (!coreVM.isGpsEnabled.value) {
            showGpsDialog.value = true
        }
    }


    BackHandler {
        activity.finishAffinity()
    }

    BottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        scaffoldState = sheetState,
        sheetContent = {
            BottomSheetContent(activateBottomSheet, sheetState, coreVM, navController)
        },
        sheetPeekHeight = if (activateBottomSheet.value) 60.dp else 0.dp,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
//            containerColor = Color.Transparent,
        ) {
            Box(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {

                DashboardMapContent(Modifier, dashboardViewModel, coreVM)

                TopBar(text = coreVM.searchText.value,
                    headerIcon = R.drawable.logo_square,
                    trailingIcon = R.drawable.search_24,
                    headerOnClick = {
                        navController.navigate(DashboardRoute.SideNavigationScreen.route)
                    },
                    textOnClick = {
                        navController.navigate(DashboardRoute.SearchScreen.route)
                    })

                Icon(
                    imageVector = Icons.Default.AddCircle,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            logi("mapLocation to currentLocation")
                            if (coreVM.isGpsEnabled.value) {
                                coreVM.mapLocation.value = coreVM.currentLocation.value
                            }
                        }
                        .align(Alignment.BottomEnd)
                        .padding(end =24.dp, bottom = 24.dp)
                        .background(MaterialTheme.colorScheme.background, CircleShape)
                        .padding(5.dp)
                )


                EnableGpsDialog(showGpsDialog, coreVM)
            }
        }
    }
}


@Composable
fun EnableGpsDialog(state: MutableState<Boolean>, coreVM: CoreViewModel) {
    if (state.value)
        Dialog(onDismissRequest = { state.value = !state.value }) {
            val openLocationSettings =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    state.value = !state.value
                    if (coreVM.isGpsEnabled.value) {
                        ioScope.launch {
                            coreVM.mapLocation.value = coreVM.currentLocation.value
                        }
                    }
                }

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Please Enable GPS for better experience !")
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "NO", modifier = Modifier.clickable { state.value = !state.value })
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Yes", modifier = Modifier.clickable {
                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        openLocationSettings.launch(intent)
                    })
                }
            }
        }
}


