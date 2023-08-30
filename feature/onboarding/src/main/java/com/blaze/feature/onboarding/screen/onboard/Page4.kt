package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blaze.core.ui.components.UploadImageLayout

@Composable
fun ParkingAreaScreen(subNavController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
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