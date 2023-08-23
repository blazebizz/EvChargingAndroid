package com.blaze.feature.startup.screen.otp

import androidx.compose.runtime.Composable


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.DashboardRoute
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(navController: NavController,
    toSentText: String,
) {

    val otpState = remember {
        mutableStateOf("")
    }

    OtpContent(
        otpState = otpState,
        sentTo = toSentText,
        onSubmitClick = { },
        onResendClick = { navController.navigate(DashboardRoute.DashboardScreen.route) }
    )
}

@Composable
internal fun OtpContent(
    sentTo: String,
    otpState: MutableState<String>,
    onSubmitClick: () -> Unit,
    onResendClick: () -> Unit,
) {
    var seconds by rememberSaveable {
        mutableStateOf(30)
    }
    var trigger by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = trigger) {
        delay(1000)
        while (seconds > 0) {
            delay(1000)
            seconds--
        }
    }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row {
            Icon(Icons.Default.ArrowBack, contentDescription = "back",modifier= Modifier.clickable {

            })
            Spacer(Modifier.width(12.dp))
            Text(text = "", fontWeight = FontWeight.Black, fontSize = 22.sp)
            Spacer(Modifier.weight(3f))
        }
        Spacer(Modifier.weight(3f))
//        Text(
//            text = "Verify OTP",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Black
//        )
//        Spacer(Modifier.height(12.dp))
        Text(
            text = "We have sent an SMS to",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(12.dp))
        Text(text = sentTo, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column {
                OtpView(length = 6, code = otpState)
                Spacer(Modifier.height(12.dp))
                Row {

                    Text(text = "$seconds seconds")
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Resend",
                        color = if (seconds == 0) Color.LightGray else Color.LightGray,
                        modifier = Modifier.clickable {
                            if (seconds == 0) {
                                onResendClick.invoke()
                                trigger = !trigger
                                seconds = 30
                            }
                        })
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(text = "Change Number.", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        Spacer(Modifier.weight(3f))
        Spacer(Modifier.height(20.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = onSubmitClick){
            Text(text  = "Submit", )
        }
        Spacer(Modifier.weight(4f))
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun OtpView(length: Int, code: MutableState<String>) {
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BasicTextField(
            value = code.value,
            onValueChange = { otpNo ->
                if (otpNo.length <= length)
                    code.value = otpNo.filter { it.isDigit() }
                if (otpNo.length == 6) {
                    keyboardController?.hide()
                }
            },
            Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                CodeInputDecoration(code.value, length)
            })
    }
}

@Composable
private fun CodeInputDecoration(code: String, length: Int) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .border(
                border = BorderStroke(2.dp, color = Color.Transparent),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Row {
            for (i in 0 until length) {
                val text = if (i < code.length) code[i].toString() else ""
                CodeEntry(text)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
private fun CodeEntry(text: String) {
    Box(
        modifier = Modifier
//            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .width(40.dp)
            .height(40.dp)
//            .clip(shape = RoundedCornerShape(8.dp))
//            .background(Color.White)
        ,

        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = text, fontSize = 20.sp)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }

    }
}
