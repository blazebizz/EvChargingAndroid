package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.feature.dashboard.screen.bottomsheet.BottomSheetContent

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


    BackHandler {
        activity.finishAffinity()
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize().padding(0.dp),
        scaffoldState = sheetState,
        sheetContent = {
            BottomSheetContent(activateBottomSheet,sheetState,coreVM,navController)
        },
        sheetPeekHeight = if (activateBottomSheet.value) 60.dp else 0.dp,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
//            containerColor = Color.Transparent,
        ) {
            DashboardContent(Modifier.padding(it),activateBottomSheet,sheetState,coreVM,navController,dashboardViewModel)
        }
    }
}





