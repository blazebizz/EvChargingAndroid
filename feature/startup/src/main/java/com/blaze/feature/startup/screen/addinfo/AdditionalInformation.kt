package com.blaze.feature.startup.screen.addinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.StartUpRoute

@Composable
fun AdditionalInfoScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Row (Modifier.fillMaxWidth()){
            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "First Name")
            }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "Last Name")
            }, modifier = Modifier.weight(1f))
        }
        Spacer(Modifier.height(16.dp))

        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(value = "+91", onValueChange = {}, label = {
                Text(text = "STD Code")
            }, textStyle = TextStyle(textAlign = TextAlign.Center),modifier = Modifier.weight(1f)
            )
            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "Mobile Number")
            }, modifier = Modifier.weight(5f))
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Continue")
        }
    }
}