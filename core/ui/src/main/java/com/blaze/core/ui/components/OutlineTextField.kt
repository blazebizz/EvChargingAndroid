package com.blaze.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun OutlinedTextField(modifier: Modifier = Modifier, label: String, value: MutableState<String>) {
    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = {
            value.value = it
        },
        label = {
            Text(text = label)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onBackground
        )
    )
}