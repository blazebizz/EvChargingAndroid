package com.blaze.feature.startup.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.CustomButtonColors
import com.blaze.core.ui.navigation.LocalCore
import com.blaze.core.ui.navigation.LocalNavigation
import com.blaze.core.utils.navigation.StartUpRoute


@Composable
fun LoginScreen() {
    val navController = LocalNavigation.current
    val coreUi = LocalCore.current
    val mobileNumber = remember { mutableStateOf("") }
    val stdCode = remember { mutableStateOf("+91") }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10.dp)).padding(10.dp)) {
            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(value = stdCode.value,
                    onValueChange = {
                        stdCode.value = it
                    },
                    label = {
                        Text(text = "STD", color = MaterialTheme.colorScheme.onPrimary)
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.weight(2f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor =  MaterialTheme.colorScheme.onPrimary,
                        focusedLabelColor =  MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor =  MaterialTheme.colorScheme.onPrimary,
                        unfocusedBorderColor =  MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor =  MaterialTheme.colorScheme.onPrimary,
                        cursorColor =  MaterialTheme.colorScheme.onPrimary
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(value = mobileNumber.value,
                    onValueChange = {
                        mobileNumber.value = it
                    },
                    label = {
                        Text(text = "Mobile Number")
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.weight(5f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (mobileNumber.value.length == 10 && stdCode.value.isNotEmpty()) {
                            navController.navigate("${StartUpRoute.MobileOtpScreen.route}/${stdCode.value}${mobileNumber.value}")
                        } else {
                            coreUi.snackbar("Invalid Mobile Number and Country Code")
                        }
                    }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor =  MaterialTheme.colorScheme.onPrimary,
                        focusedLabelColor =  MaterialTheme.colorScheme.onPrimary,
                        focusedBorderColor =  MaterialTheme.colorScheme.onPrimary,
                        unfocusedBorderColor =  MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor =  MaterialTheme.colorScheme.onPrimary,
                        cursorColor =  MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                text = "Continue", colors = CustomButtonColors.colors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                    bodyColor = MaterialTheme.colorScheme.background
                ), modifier = Modifier.fillMaxWidth()
            ) {
                if (mobileNumber.value.length == 10 && stdCode.value.isNotEmpty()) {
                    navController.navigate("${StartUpRoute.MobileOtpScreen.route}/${stdCode.value}${mobileNumber.value}")
                } else {
                    coreUi.snackbar("Invalid Mobile Number and Country Code")
                }
            }

        }
        Spacer(Modifier.height(16.dp))
    }
}

