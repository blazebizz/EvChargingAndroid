package com.blaze.feature.dashboard.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.feature.dashboard.BuildConfig
import com.blaze.feature.dashboard.screen.dashboard.DashboardScreen
import com.blaze.feature.dashboard.screen.dashboard.DashboardViewModel
import com.blaze.feature.dashboard.screen.search.SearchScreen
import com.blaze.feature.dashboard.screen.search.SearchViewModel
import com.blaze.feature.dashboard.screen.sidenav.SideNavigationScreen
import com.blaze.feature.dashboard.screen.sidenav.SideNavigationScreenViewModel
import com.google.android.libraries.places.api.Places


fun NavGraphBuilder.dashboardNavGraph() {
    composable(route = DashboardRoute.DashboardScreen.route) {
        val context = LocalContext.current
        Places.initialize(context, BuildConfig.MAPS_API_KEY)
        val dashboardViewModel = hiltViewModel<DashboardViewModel>()
        dashboardViewModel.placesClient = Places.createClient(context)
        DashboardScreen(dashboardViewModel)
    }
    composable(route = DashboardRoute.SearchScreen.route) {
        val context = LocalContext.current
        Places.initialize(context, BuildConfig.MAPS_API_KEY)
        val viewModel= hiltViewModel<SearchViewModel>()
        viewModel.placesClient = Places.createClient(context)
        SearchScreen(viewModel)
    }
    composable(route = DashboardRoute.SideNavigationScreen.route) {
        val viewModel = hiltViewModel<SideNavigationScreenViewModel>()
        SideNavigationScreen(viewModel)
    }
}