package com.blaze.feature.onboarding.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.feature.onboarding.screen.OnBoardingViewModel
import com.blaze.feature.onboarding.screen.onboard.OnBoardingCompleteScreen
import com.blaze.feature.onboarding.screen.onboard.OnBoardingScreen


fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavController,
    coreUi: CoreUiViewModel,
    onBoardingViewModel: OnBoardingViewModel
){
    composable(OnBoardingRoute.OnBoardingScreen.route){
        OnBoardingScreen(navController,onBoardingViewModel,coreUi)
    }

    composable(OnBoardingRoute.BoardingCompleteScreen.route){
        OnBoardingCompleteScreen(navController,onBoardingViewModel,coreUi)
    }



}