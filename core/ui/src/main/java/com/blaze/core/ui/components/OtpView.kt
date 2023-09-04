package com.blaze.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun OtpView(length: Int, code: MutableState<String>) {
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        BasicTextField(value = code.value,
            onValueChange = { otpNo ->
                if (otpNo.length <= length) code.value = otpNo.filter { it.isDigit() }
                if (otpNo.length == length) {
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