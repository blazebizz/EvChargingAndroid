package com.blaze.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blaze.feature.onboarding.screen.onboard.InfoScreen
import com.blaze.feature.onboarding.screen.onboard.ParkingAreaScreen
import com.blaze.feature.onboarding.screen.onboard.TermScreen
import com.blaze.feature.onboarding.screen.onboard.UploadDocumentScreen
import com.blaze.feature.onboarding.screen.onboard.VehicleSelectionScreen

@Composable
fun OnBoardingSubNavGraph(subNavController: NavHostController) {
    NavHost(
        navController = subNavController,
        startDestination = OnBoardingSubScreen.OnBoardVehicleSelectionScreen.name
    ) {
        composable(OnBoardingSubScreen.OnBoardVehicleSelectionScreen.name) {
            VehicleSelectionScreen(subNavController)
        }
        composable(OnBoardingSubScreen.OnBoardingInfoScreen.name) {
            InfoScreen(subNavController)
        }
        composable(OnBoardingSubScreen.OnBoardingUploadDocScreen.name) {
            UploadDocumentScreen(subNavController)
        }
        composable(OnBoardingSubScreen.OnBoardingParkingAreaScreen.name) {
            ParkingAreaScreen(subNavController)
        }
        composable(OnBoardingSubScreen.OnBoardingTermsScreen.name) {
            TermScreen(subNavController)
        }
    }
}


enum class OnBoardingSubScreen {
    OnBoardVehicleSelectionScreen, OnBoardingInfoScreen, OnBoardingUploadDocScreen, OnBoardingParkingAreaScreen,OnBoardingTermsScreen;
}