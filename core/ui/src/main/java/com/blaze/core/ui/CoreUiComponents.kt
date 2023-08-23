package com.blaze.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InitSubUiComponents(model: CoreUiViewModel) {

    //region Snackbar
    val showSnackBar = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = model.snackbarValue.value.first) {
        showSnackBar.value = false
        if (model.snackbarValue.value.first) {
            showSnackBar.value = true
        }
    }
    if (showSnackBar.value) {
        Snackbar(Modifier.padding(16.dp)) {
            Text(text = model.snackbarValue.value.second)
        }
    }

    //endregion

}