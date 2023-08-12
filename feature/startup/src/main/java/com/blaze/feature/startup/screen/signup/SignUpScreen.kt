package com.blaze.feature.startup.screen.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.StartUpRoute

@Composable
fun SignUpScreen(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
//                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription ="google" )
                Text(text = "Sign up with Google")
            }
        }

        Spacer(Modifier.height(10.dp))
        Row {
            Spacer(
                Modifier
                    .height(1.dp)
                    .weight(1f)
            )
            Text(text = " or ")
            Spacer(
                Modifier
                    .height(1.dp)
                    .weight(1f)
            )
        }
        Spacer(Modifier.height(10.dp))

        Text(text = "Email")
        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        Text(text = "Password")
        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        Text(text = "Password")
        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
                         navController.navigate(StartUpRoute.AdditionalDetailsScreen.route)

                         }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sign Up")
        }


    }
}