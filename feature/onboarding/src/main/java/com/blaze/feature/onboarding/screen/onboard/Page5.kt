package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blaze.core.ui.components.OutLinedTextFieldSpinner
import com.blaze.core.ui.components.OutlinedTextField
import com.blaze.core.utils.util.bankList
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun BankDetailsScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {

    Column(
        Modifier.fillMaxSize()
    ) {
        Text(text = "Bank Information")
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.accNameHolder,
            label = "Account Holder Name"
        )

//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(), value = viewModel.bankName, label = "Bank Name"
//        )
        val spinnerState = remember { mutableStateOf(false) }
        OutLinedTextFieldSpinner(
            spinnerState = spinnerState,
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.bankName,
            list = bankList,
            label = "Bank Name"
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.accNumber,
            label = "Account Number",
            keyboardType = KeyboardType.Number,
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.accConfirmNumber,
            label = "Confirm Account Number",
            keyboardType = KeyboardType.Number,
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(), value = viewModel.ifscCode, label = "IFSC code"
        )
    }
}