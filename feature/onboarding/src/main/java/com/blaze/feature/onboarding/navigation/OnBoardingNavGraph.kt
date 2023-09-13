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


fun NavGraphBuilder.onBoardingNavGraph(navController: NavController, coreUi: CoreUiViewModel){
    composable(OnBoardingRoute.OnBoardingScreen.route){
        val viewModel = hiltViewModel<OnBoardingViewModel>()
        OnBoardingScreen(navController,viewModel,coreUi)
    }
    composable(OnBoardingRoute.OnBoardingCompleteScreen.route){
        val viewModel = hiltViewModel<OnBoardingViewModel>()
        OnBoardingCompleteScreen(navController,viewModel,coreUi)
    }
}