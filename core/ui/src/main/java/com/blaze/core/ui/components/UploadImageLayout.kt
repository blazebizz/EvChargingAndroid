package com.blaze.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.R
import com.blaze.core.ui.ui.theme.LightGray
import com.blaze.core.ui.ui.theme.MarbleWhite


@Composable
fun UploadImageLayout(title: String, onClick: () -> Unit) {
    Column(
        Modifier
            .bounceClick { onClick() }
            .size(200.dp)) {
        Box(
            Modifier
                .fillMaxHeight(1f)
                .aspectRatio(1f)
                .background(MarbleWhite, RoundedCornerShape(10.dp))
                .border(
                    2.dp,
                    LightGray, RoundedCornerShape(10.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = null
            )
        }
        Text(text = title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

    }
}