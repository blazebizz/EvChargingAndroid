package com.blaze.feature.dashboard.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.TopBar
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.util.ioScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    activateSheet: MutableState<Boolean>,
    sheetState: BottomSheetScaffoldState,
    coreVM: CoreViewModel,
    navController: NavController
) {
    Box(modifier.fillMaxSize()) {
        DashboardMapContent(Modifier) {
            ioScope.launch {
                activateSheet.value = !activateSheet.value
            }
        }

        TopBar(text = coreVM.searchText.value,
            headerIcon = R.drawable.logo_square,
            trailingIcon = R.drawable.search_24,
            headerOnClick = {
                navController.navigate(DashboardRoute.SideNavigationScreen.route)
            },
            textOnClick = {
                navController.navigate(DashboardRoute.SearchScreen.route)
            })
    }
}