package com.blaze.feature.startup.screen.createuser

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.CustomButtonColors

@Composable
fun CreateUserScreen(
    navController: NavController,
    coreVm: CoreViewModel,
    viewModel: CreateUserViewModel,
    stdCode: String?,
    displayName: String?,
    mobileNumber: String?
) {

}


@Composable
@Preview(showBackground = true)
fun PreviewCreateUserScreen() {
    val mobileNumber = "99999"
    val stdCode = ""
    val userName = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = userName.value, onValueChange = {
                userName.value = it
            },

            label = {
                Text(text = "Full Name")
            }, textStyle = TextStyle(
                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold
            ), modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
            ), keyboardActions = KeyboardActions(onDone = {

            }), colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(value = stdCode, onValueChange = {

            }, enabled = false, label = {
                Text(text = "STD", color = MaterialTheme.colorScheme.onPrimary)
            }, textStyle = TextStyle(
                textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold
            ), modifier = Modifier.weight(2f), keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
            ), colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                disabledBorderColor = MaterialTheme.colorScheme.onPrimary,
                disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary
            )
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(value = mobileNumber, onValueChange = {

            }, enabled = false, label = {
                Text(text = "Mobile Number")
            }, textStyle = TextStyle(
                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold
            ), modifier = Modifier.weight(5f), keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
            ), keyboardActions = KeyboardActions(onDone = {

            }), colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                disabledBorderColor = MaterialTheme.colorScheme.onPrimary,
                disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary
            )
            )
        }

        Spacer(modifier = Modifier.weight(4f))
        Button(
            text = "Continue", colors = CustomButtonColors.colors(
                textColor = MaterialTheme.colorScheme.onBackground,
                bodyColor = MaterialTheme.colorScheme.background
            ), modifier = Modifier.fillMaxWidth()
        ) {
            //todo
        }
        Spacer(Modifier.height(16.dp))
    }
}