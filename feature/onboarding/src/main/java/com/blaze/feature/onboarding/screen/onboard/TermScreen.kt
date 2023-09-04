package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun TermScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {

    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = viewModel.tcChecked.value, onCheckedChange = {
                viewModel.tcChecked.value = !viewModel.tcChecked.value
            })
            Text("I accept the Terms & Conditions !")
        }
    }
}