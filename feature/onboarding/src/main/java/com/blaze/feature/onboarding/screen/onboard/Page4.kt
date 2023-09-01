package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.blaze.core.ui.components.UploadImageLayout
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun ParkingAreaScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {
    Column(
        Modifier
            .fillMaxSize()) {
        UploadImageLayout(title = "Aadhar Front") {

        }
        UploadImageLayout(title = "Aadhar Back") {

        }
        UploadImageLayout(title = "Pan") {

        }
        UploadImageLayout(title = "Electric Bill") {

        }
        UploadImageLayout(title = "Any Other Document(optional)") {

        }
    }
}