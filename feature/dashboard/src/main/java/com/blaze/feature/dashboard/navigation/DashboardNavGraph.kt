package com.blaze.feature.dashboard.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.feature.dashboard.screen.dashboard.DashboardScreen
import com.blaze.feature.dashboard.screen.search.SearchScreen
import com.blaze.feature.dashboard.screen.sidenav.SideNavigationScreen
import com.blaze.feature.dashboard.screen.sidenav.SideNavigationScreenViewModel


fun NavGraphBuilder.dashboardNavGraph(navController: NavController, coreUi: CoreUiViewModel) {
    composable(route = DashboardRoute.DashboardScreen.route) {
        DashboardScreen(navController)
    }
    composable(route = DashboardRoute.SearchScreen.route) {
        SearchScreen(navController)
    }
    composable(route = DashboardRoute.SideNavigationScreen.route) {
        val viewModel = hiltViewModel<SideNavigationScreenViewModel>()
        SideNavigationScreen(navController,viewModel,coreUi)
    }
}