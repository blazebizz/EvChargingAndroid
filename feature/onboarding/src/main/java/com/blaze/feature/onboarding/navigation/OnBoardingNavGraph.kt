package com.blaze.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.feature.onboarding.screen.OnBoardingViewModel
import com.blaze.feature.onboarding.screen.onboard.OnBoardingCompleteScreen
import com.blaze.feature.onboarding.screen.onboard.OnBoardingScreen
import com.blaze.feature.onboarding.screen.onboard.OnBoardingStatusScreen


fun NavGraphBuilder.onBoardingNavGraph(
    onBoardingViewModel: OnBoardingViewModel
){
    composable(OnBoardingRoute.OnBoardingScreen.route){
        OnBoardingScreen(onBoardingViewModel)
    }

    composable(OnBoardingRoute.BoardingCompleteScreen.route){
        OnBoardingCompleteScreen()
    }
    composable(OnBoardingRoute.BoardingStatusScreen.route){
        OnBoardingStatusScreen(onBoardingViewModel)
    }
}