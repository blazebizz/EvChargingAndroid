package com.blaze.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blaze.feature.onboarding.screen.onboard.basicinfo.BasicInfoScreen
import com.blaze.feature.onboarding.screen.onboard.documentInfo.DocumentInfoScreen
import com.blaze.feature.onboarding.screen.onboard.electricdetail.ElectricDetailScreen
import com.blaze.feature.onboarding.screen.onboard.uploaddoc.UploadDocumentScreen

@Composable
fun OnBoardingSubNavGraph(subNavController: NavHostController) {
    NavHost(
        navController = subNavController,
        startDestination = OnBoardingSubScreen.BasicInfoScreen.name
    ) {
        composable(OnBoardingSubScreen.BasicInfoScreen.name) {
            BasicInfoScreen(subNavController)
        }
        composable(OnBoardingSubScreen.DocumentInfoScreen.name) {
            DocumentInfoScreen(subNavController)
        }
        composable(OnBoardingSubScreen.StepThreeScreen.name) {
            ElectricDetailScreen(subNavController)
        }
        composable(OnBoardingSubScreen.StepFourScreen.name) {
            UploadDocumentScreen(subNavController)
        }
    }
}


enum class OnBoardingSubScreen {
    BasicInfoScreen, DocumentInfoScreen, StepThreeScreen, StepFourScreen;
}