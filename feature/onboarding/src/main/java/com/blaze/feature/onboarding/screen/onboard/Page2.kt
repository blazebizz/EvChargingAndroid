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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blaze.core.ui.components.OutLinedTextFieldSpinner
import com.blaze.core.ui.components.OutlinedTextField
import com.blaze.core.utils.util.stateList
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun InfoScreen(viewModel: OnBoardingViewModel) {
    val spinnerExpand = remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Text(text = "Basic Information")
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.fullName,
            label = "Full Name"
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.mobile,
            label = "Mobile Number",
            keyboardType = KeyboardType.Phone,
            maxChar = 10
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Address")
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.addressL1,
            label = "Address Line 1"
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.addressL2,
            label = "Address Line 2"
        )

        OutLinedTextFieldSpinner(
            spinnerState = spinnerExpand,
            modifier = Modifier.fillMaxWidth(),
            text = viewModel.state,
            list = stateList,
            label = "State"
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.pincode,
            label = "Pincode",
            keyboardType = KeyboardType.Number,
            maxChar = 6
        )

        Spacer(modifier = Modifier.height(5.dp))


    }
}