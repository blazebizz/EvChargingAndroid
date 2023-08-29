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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    val stdCode = remember { mutableStateOf("+91") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
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
        Button(onClick = {
            if (mobileNumber.value.length == 10 && stdCode.value.isNotEmpty()) {
                navController.navigate("${StartUpRoute.MobileOtpScreen.route}/${stdCode.value}${mobileNumber.value}")
            } else {
                coreUi.snackbar("Invalid Mobile Number and Country Code")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Continue")
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
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
}*/
