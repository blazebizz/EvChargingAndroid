package com.blaze.classicbeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.blaze.core.ui.CoreViewModel
import com.blaze.feature.dashboard.navigation.dashboardNavGraph
import com.blaze.feature.onboarding.navigation.onBoardingNavGraph
import com.blaze.feature.onboarding.screen.OnBoardingViewModel
import com.blaze.feature.startup.navigation.startUpNavGraph

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController,
    coreUi: CoreViewModel,
    onBoardingViewModel: OnBoardingViewModel
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        startUpNavGraph(navController,coreUi)
        dashboardNavGraph(navController,coreUi)
        onBoardingNavGraph(navController,coreUi,onBoardingViewModel)
    }
}