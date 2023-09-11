package com.blaze.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.R
import com.blaze.core.ui.ui.theme.SeaSalt
import com.blaze.core.utils.util.toBitmap


@Composable
fun UploadImageLayout(title: String, byteArray: MutableState<ByteArray?>, onClick: () -> Unit) {

    val context = LocalContext.current
    val contentResolver = context.contentResolver // Use the appropriate context

    Column(
        Modifier
            .padding(4.dp)
            .bounceClick { onClick() }
            .size(200.dp)) {
        Box(
            Modifier
                .fillMaxHeight(1f)
                .aspectRatio(1f)
                .background(Color.Transparent, RoundedCornerShape(10.dp))
                .border(
                    2.dp, SeaSalt, RoundedCornerShape(10.dp)
                ), contentAlignment = Alignment.Center
        ) {
            if (byteArray.value != null) {

                Image(
                    bitmap = byteArray.value!!.toBitmap().asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,

                )

            }
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_a_photo_24),
                    contentDescription = null
                )
                Text(text = title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }

    }
}