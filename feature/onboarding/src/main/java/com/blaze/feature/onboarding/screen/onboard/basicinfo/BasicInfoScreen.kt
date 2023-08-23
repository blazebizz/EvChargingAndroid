package com.blaze.feature.onboarding.screen.onboard.basicinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun BasicInfoScreen(subNavController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Basic Info Screen")
    }
}
