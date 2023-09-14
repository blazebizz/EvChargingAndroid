package com.blaze.core.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    readOnly:Boolean = false,
    isError:Boolean = false,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    maxChar:Int = 0
) {
    androidx.compose.material3.OutlinedTextField(modifier = modifier,
        value = value.value,
        onValueChange = {
            if (maxChar==(0)){
                value.value = it
            }else{
                if (it.length < maxChar+1){
                    value.value = it
                }
            }
        },
        label = {
            Text(text = label)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        ),
        singleLine = true,
        readOnly = readOnly,
        isError = isError
    )
}