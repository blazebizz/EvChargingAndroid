package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.blaze.core.ui.components.SelectImageDialog
import com.blaze.core.ui.components.UploadImageLayout
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun ParkingAreaScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {

    Column(Modifier.fillMaxSize()) {

        val showDialog = rememberSaveable { mutableStateOf(false) }
        val currentSelected = remember { mutableStateOf(viewModel.pkImage1) }
        val scrollState = rememberScrollState()
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row {
                UploadImageLayout(title = "", viewModel.pkImage1) {
                    showDialog.value = true
                    currentSelected.value = viewModel.pkImage1
                }

                UploadImageLayout(title = "", viewModel.pkImage2) {
                    showDialog.value = true
                    currentSelected.value = viewModel.pkImage2
                }
            }
            Row {
                UploadImageLayout(title = "", viewModel.pkImage3) {
                    showDialog.value = true
                    currentSelected.value = viewModel.pkImage3
                }

                UploadImageLayout(title = "", viewModel.pkImage4) {
                    showDialog.value = true
                    currentSelected.value = viewModel.pkImage4
                }
            }

        }

        if (showDialog.value) SelectImageDialog(showDialog) {
            currentSelected.value.value = null
            currentSelected.value.value = it
        }
    }
}