package com.blaze.feature.startup.screen.forgetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(value = "", onValueChange = {

        })
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "We will send a recovery link to the email address for resetting your password.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Forgot Password")
        }
        Spacer(modifier = Modifier.weight(1f))

    }
}