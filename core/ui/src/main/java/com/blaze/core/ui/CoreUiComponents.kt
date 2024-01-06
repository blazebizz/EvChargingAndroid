package com.blaze.core.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.components.LoadingDialog
import kotlinx.coroutines.delay


val DefaultShape = RoundedCornerShape(20.dp)


fun Modifier.defaultBackground() = composed {
    this.background(MaterialTheme.colorScheme.surfaceVariant, DefaultShape)
}

@Composable
fun InitSubUiComponents(model: CoreViewModel) {
    val context = LocalContext.current
    //region Snackbar
    val showSnackBar = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = model.snackbarValue.value.first) {
        showSnackBar.value = false
        if (model.snackbarValue.value.first) {
            showSnackBar.value = true
        }
    }
    if (showSnackBar.value) {
        Snackbar(
            Modifier
                .statusBarsPadding()
                .padding(16.dp)
                .height(50.dp)
        ) {
            Text(text = model.snackbarValue.value.second)
        }
    }
    //endregion

    //region toast
    LaunchedEffect(key1 = model.toast.value) {
        if (model.toast.value.isNotBlank()) Toast.makeText(
            context, model.toast.value, Toast.LENGTH_SHORT
        ).show()
        delay(2000)
    }

    //endregion


    //region loading
    if (model.loading.value) LoadingDialog()
    //endregion


}


