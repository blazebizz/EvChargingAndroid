package com.blaze.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blaze.feature.onboarding.screen.OnBoardingViewModel
import com.blaze.feature.onboarding.screen.onboard.BankDetailsScreen
import com.blaze.feature.onboarding.screen.onboard.InfoScreen
import com.blaze.feature.onboarding.screen.onboard.ParkingAreaScreen
import com.blaze.feature.onboarding.screen.onboard.TermScreen
import com.blaze.feature.onboarding.screen.onboard.UploadDocumentScreen
import com.blaze.feature.onboarding.screen.onboard.VehicleSelectionScreen

@Composable
fun OnBoardingSubNavGraph(subNavController: NavHostController, viewModel: OnBoardingViewModel) {
    NavHost(
        navController = subNavController,
        startDestination = OnBoardingSubScreen.Page1.name
    ) {
        composable(OnBoardingSubScreen.Page1.name) {
            VehicleSelectionScreen(subNavController,viewModel)
        }
        composable(OnBoardingSubScreen.Page2.name) {
            InfoScreen(subNavController,viewModel)
        }
        composable(OnBoardingSubScreen.Page3.name) {
            UploadDocumentScreen(subNavController,viewModel)
        }
        composable(OnBoardingSubScreen.Page4.name) {
            ParkingAreaScreen(subNavController,viewModel)
        }
        composable(OnBoardingSubScreen.Page5.name) {
            BankDetailsScreen(subNavController,viewModel)
        }
        composable(OnBoardingSubScreen.OnBoardingTermsScreen.name) {
            TermScreen(subNavController,viewModel)
        }
    }
}


enum class OnBoardingSubScreen {
    Page1, Page2, Page3, Page4,Page5,OnBoardingTermsScreen;
}