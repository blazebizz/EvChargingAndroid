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
fun InfoScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {

    val scrollState = rememberScrollState()
    val text = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)) {
        Text(text = "Basic Information")
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label =  "Full Name")

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = "Mobile Number")
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Address")
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = "Address Line 1")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = "Address Line 2")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = "state")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = "Pincode")

        Spacer(modifier = Modifier.height(5.dp))

     
    }
}