package com.blaze.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun OutlinedButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier
        .bounceClick { onClick() }
        .border(2.dp, MaterialTheme.colorScheme.onBackground)
        .padding(14.dp), contentAlignment = Alignment.Center) {
        Text(
            text = text.uppercase(),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}