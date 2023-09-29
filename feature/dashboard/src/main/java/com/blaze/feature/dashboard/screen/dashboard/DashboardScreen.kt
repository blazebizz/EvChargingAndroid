package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
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
import com.blaze.core.ui.R
import com.blaze.core.ui.components.TopBar
import com.blaze.core.utils.navigation.DashboardRoute
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
            Box(Modifier.padding(it).fillMaxSize()) {
                DashboardMapContent(Modifier,dashboardViewModel,coreVM)

                TopBar(text = coreVM.searchText.value,
                    headerIcon = R.drawable.logo_square,
                    trailingIcon = R.drawable.search_24,
                    headerOnClick = {
                        navController.navigate(DashboardRoute.SideNavigationScreen.route)
                    },
                    textOnClick = {
                        navController.navigate(DashboardRoute.SearchScreen.route)
                    })
            }        }
    }
}





