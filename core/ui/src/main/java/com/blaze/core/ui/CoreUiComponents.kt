package com.blaze.core.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.ui.theme.PrimaryColor

@Composable
fun InitSubUiComponents(model: CoreUiViewModel) {
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
    LaunchedEffect(key1 = model.toast.value ){
        Toast.makeText(context, model.toast.value, Toast.LENGTH_SHORT).show()
    }




    //endregion


}


