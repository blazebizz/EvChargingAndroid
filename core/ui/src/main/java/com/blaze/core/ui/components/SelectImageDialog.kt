package com.blaze.core.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.blaze.core.utils.util.saveBitmapToFile

@Composable
fun SelectImageDialog(state: MutableState<Boolean>, onImageSelected: (Uri) -> Unit) {

    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                onImageSelected(it)
                state.value = false
            }
        }

    val takePicture =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            if (bitmap != null) {
                val uri = saveBitmapToFile(bitmap, context)
                if (uri != null) {
                    onImageSelected(uri)
                }
                state.value = false
            }
        }

    Dialog(onDismissRequest = { state.value = false }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(16.dp)
        ) {
            Text(text = "Upload Image", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.background)
            Spacer(Modifier.height(16.dp))
            Button(
                text = "Select Image",
                modifier = Modifier.fillMaxWidth(),
                colors = CustomButtonColors.colors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                    bodyColor = MaterialTheme.colorScheme.background
                )
            ) {
                launcher.launch("image/*")
            }
            Spacer(Modifier.height(16.dp))
            Button(text = "Capture Image", modifier = Modifier.fillMaxWidth()) {
                takePicture.launch()
            }
        }
    }

}








