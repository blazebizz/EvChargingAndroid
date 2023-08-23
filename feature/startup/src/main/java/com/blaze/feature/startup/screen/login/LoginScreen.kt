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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.StartUpRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController, coreUi: CoreUiViewModel) {
    val mobileNumber = remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = "+91",
                onValueChange = {},
                label = {
                    Text(text = "STD Code")
                },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(value = "", onValueChange = {
                mobileNumber.value = it
            }, label = {
                Text(text = "Mobile Number")
            }, modifier = Modifier.weight(5f))
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (mobileNumber.value.length == 10){
                navController.navigate("${StartUpRoute.MobileOtpScreen.route}/${mobileNumber.value}")
            }else{
                coreUi.snackbar("Invalid Mobile Number")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Continue")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OldLoginScreen(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.Main).launch {}
    }

    val showLogin = remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(DashboardRoute.DashboardScreen.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
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
            Text(text = "Forgot ?", modifier = Modifier
                .clickable {
                    navController.navigate(StartUpRoute.ForgotPasswordScreen.route)
                }
                .fillMaxWidth(), textAlign = TextAlign.End)
            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate(DashboardRoute.DashboardScreen.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
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
