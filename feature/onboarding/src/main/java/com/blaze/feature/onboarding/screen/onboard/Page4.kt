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
    val showDialog = rememberSaveable { mutableStateOf(false) }

    val currentSelected = remember { mutableStateOf(viewModel.aadharFrontUri) }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row {
            UploadImageLayout(title = "Aadhar Front", viewModel.aadharFrontUri) {
                showDialog.value = true
                currentSelected.value = viewModel.aadharFrontUri
            }

            UploadImageLayout(title = "Aadhar Back", viewModel.aadharBackUri) {
                showDialog.value = true
                currentSelected.value = viewModel.aadharBackUri
            }
        }
        UploadImageLayout(title = "Pan", viewModel.panUri) {
            showDialog.value = true
            currentSelected.value = viewModel.panUri
        }
        UploadImageLayout(title = "Electric Bill", viewModel.electricBillUri) {
            showDialog.value = true
            currentSelected.value = viewModel.electricBillUri
        }
        UploadImageLayout(title = "Any Other Document\n(optional)", viewModel.otherUri) {
            showDialog.value = true
            currentSelected.value = viewModel.otherUri
        }
    }

    if (showDialog.value) SelectImageDialog(showDialog) {
        currentSelected.value.value = null
        currentSelected.value.value = it
    }
}