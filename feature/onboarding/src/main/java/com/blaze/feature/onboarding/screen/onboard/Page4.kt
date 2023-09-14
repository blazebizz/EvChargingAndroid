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
fun DocumentImageScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {
    val showDialog = rememberSaveable { mutableStateOf(false) }

    val currentSelected = remember { mutableStateOf(viewModel.aadharFrontByteArray) }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row {
            UploadImageLayout(title = "Aadhar Front", viewModel.aadharFrontByteArray) {
                showDialog.value = true
                currentSelected.value = viewModel.aadharFrontByteArray
            }

            UploadImageLayout(title = "Aadhar Back", viewModel.aadharBackByteArray) {
                showDialog.value = true
                currentSelected.value = viewModel.aadharBackByteArray
            }
        }
        UploadImageLayout(title = "Pan", viewModel.panByteArray) {
            showDialog.value = true
            currentSelected.value = viewModel.panByteArray
        }
        UploadImageLayout(title = "Electric Bill", viewModel.electricBillByteArray) {
            showDialog.value = true
            currentSelected.value = viewModel.electricBillByteArray
        }
        UploadImageLayout(title = "Any Other Document\n(optional)", viewModel.otherByteArray) {
            showDialog.value = true
            currentSelected.value = viewModel.otherByteArray
        }
    }

    if (showDialog.value) SelectImageDialog(showDialog) {
        currentSelected.value.value = null
        currentSelected.value.value = it
    }
}