package com.blaze.feature.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.feature.dashboard.screen.dashboard.DashboardScreen
import com.blaze.feature.dashboard.screen.sidenav.SideNavigationScreen


fun NavGraphBuilder.dashboardNavGraph(navController: NavController) {
    composable(route = DashboardRoute.DashboardScreen.route) {
        DashboardScreen(navController)
    }
    composable(route = DashboardRoute.SideNavigationScreen.route) {
        SideNavigationScreen(navController)
    }
}