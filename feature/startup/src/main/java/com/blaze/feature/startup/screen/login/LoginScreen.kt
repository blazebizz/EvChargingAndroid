package com.blaze.feature.startup.screen.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.StartUpRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.Main).launch {
        }
    }

    val showLogin = remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.weight(1f))

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
//                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription ="google" )
                Text(text = "Sign in with Google")
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

        if (!showLogin.value) {
            Button(onClick = { showLogin.value = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login With Email")
            }
        } else {
            Text(text = "Email")
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))

            Text(text = "Password")
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Forgot ?", modifier = Modifier
                    .clickable {
                        navController.navigate(StartUpRoute.ForgotPasswordScreen.route)
                    }
                    .fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Spacer(Modifier.height(8.dp))

            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Sign In")
            }
        }

        Spacer(Modifier.height(16.dp))
        Row(Modifier.align(Alignment.End)) {
            Text(text = "Don't have an account ?")
            Text(text = "Sign Up", modifier = Modifier.clickable {
                navController.navigate(StartUpRoute.SignUpScreen.route)
            })
        }
        Spacer(Modifier.height(16.dp))

    }
}