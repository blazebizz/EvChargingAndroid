package com.blaze.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.feature.onboarding.screen.onboard.OnBoardingScreen


fun NavGraphBuilder.onBoardingNavGraph(navController: NavController, coreUi: CoreUiViewModel){
    composable(OnBoardingRoute.OnBoardingScreen.route){
        OnBoardingScreen(navController)
    }
}