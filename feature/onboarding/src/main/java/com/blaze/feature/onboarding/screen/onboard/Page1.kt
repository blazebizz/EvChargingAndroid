package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun VehicleSelectionScreen(subNavController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(200.dp)) {
            Box(
                Modifier
                    .padding(5.dp)
                    .weight(1f)) {
                RadioButton(selected = true, onClick = { /*TODO*/ })
            }
            Box(
                Modifier
                    .padding(5.dp)
                    .weight(1f)) {
                RadioButton(selected = true, onClick = { /*TODO*/ })
            }
        }

        Text(text = "Prerequisites text")
    }
}