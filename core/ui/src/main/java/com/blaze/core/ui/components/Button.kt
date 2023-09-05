package com.blaze.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    colors: CustomButtonColor = CustomButtonColors.colors(),
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .bounceClick { onClick() }
        .background(colors.bodyColor, RoundedCornerShape(5.dp))
        .padding(14.dp),
        contentAlignment = Alignment.Center) {
        Text(
            text = text.uppercase(),
            color = colors.textColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}


@Composable

fun OutlinedButton(text: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onBackground, onClick: () -> Unit) {
    Box(modifier = modifier
        .bounceClick { onClick() }
        .border(2.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(5.dp))
        .padding(14.dp), contentAlignment = Alignment.Center) {
        Text(
            text = text.uppercase(),
            color = color,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}

@Immutable
object CustomButtonColors {
    @Composable
    fun colors(
        textColor: Color = MaterialTheme.colorScheme.background, bodyColor: Color = MaterialTheme.colorScheme.onBackground
    ): CustomButtonColor = CustomButtonColor(
        textColor, bodyColor
    )

}

data class CustomButtonColor(
    val textColor: Color = Color.White,
    val bodyColor: Color = Color.Black
)