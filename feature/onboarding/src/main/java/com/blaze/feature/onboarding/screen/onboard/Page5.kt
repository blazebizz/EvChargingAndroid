package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun BankDetailsScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {
    val text = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Text(text = "Bank Information")
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            label = "Account Holder Name")
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            label =  "Account Number")
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            label ="Confirm Account Number")
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            label ="IFSC code")
    }
}