package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blaze.core.ui.components.OutlinedTextField
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun UploadDocumentScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {
    val text = rememberSaveable {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Text(text = "Document Information")
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.aadharNumber,
            label = "Aadhar Number"
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.panNumber,
            label = "Pan Number"
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.electricProvide,
            label = "Electric Service Provider"
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.electricBillNumber,
            label = "Bill Account Number"
        )

    }
}
