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
import com.blaze.feature.onboarding.screen.onboard.OnBoardingStatusScreen


fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavController,
    coreVm: CoreUiViewModel,
    onBoardingViewModel: OnBoardingViewModel
){
    composable(OnBoardingRoute.OnBoardingScreen.route){
        OnBoardingScreen(navController,onBoardingViewModel,coreVm)
    }

    composable(OnBoardingRoute.BoardingCompleteScreen.route){
        OnBoardingCompleteScreen(navController,onBoardingViewModel,coreVm)
    }
    composable(OnBoardingRoute.BoardingStatusScreen.route){
        OnBoardingStatusScreen(navController,onBoardingViewModel,coreVm)
    }



}