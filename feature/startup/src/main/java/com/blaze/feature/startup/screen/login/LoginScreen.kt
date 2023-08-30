package com.blaze.feature.startup.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.ui.components.Button
import com.blaze.core.utils.navigation.StartUpRoute


@Composable
fun LoginScreen(navController: NavController, coreUi: CoreUiViewModel) {
    val mobileNumber = remember { mutableStateOf("") }
    val stdCode = remember { mutableStateOf("+91") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = stdCode.value,
                onValueChange = {
                    stdCode.value = it
                },
                label = {
                    Text(text = "Code")
                },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(value = mobileNumber.value, onValueChange = {
                mobileNumber.value = it
            }, label = {
                Text(text = "Mobile Number")
            }, modifier = Modifier.weight(5f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (mobileNumber.value.length == 10 && stdCode.value.isNotEmpty()) {
                            navController.navigate("${StartUpRoute.MobileOtpScreen.route}/${stdCode.value}${mobileNumber.value}")
                        } else {
                            coreUi.snackbar("Invalid Mobile Number and Country Code")
                        }
                    }
                )
            )
        }

        Spacer(Modifier.height(16.dp))
        Button(text = "Continue", modifier = Modifier.fillMaxWidth()) {
            if (mobileNumber.value.length == 10 && stdCode.value.isNotEmpty()) {
                navController.navigate("${StartUpRoute.MobileOtpScreen.route}/${stdCode.value}${mobileNumber.value}")
            } else {
                coreUi.snackbar("Invalid Mobile Number and Country Code")
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

