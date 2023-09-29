package com.blaze.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blaze.core.ui.DefaultShape
import com.blaze.core.ui.R
import com.blaze.core.ui.components.bounceClick
import com.blaze.core.ui.defaultBackground


@Composable
fun TopBar(
    text: String,
    headerIcon: Int  = R.drawable.logo_square,
    headerOnClick: () -> Unit = {},
    trailingIcon: Int,
    trailingOnClick: () -> Unit = {},
    textOnClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .padding(16.dp)
            .shadow(4.dp, shape = DefaultShape)
            .defaultBackground()

//            .border(1.dp,MaterialTheme.colorScheme.onBackground, DefaultShape)
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter =painterResource( headerIcon), contentDescription = null,
            Modifier
                .fillMaxHeight()
                .clickable {
                    headerOnClick()
                }
                .clip(CircleShape))

        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .bounceClick { textOnClick() },
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.width(12.dp))
        Icon(
            painter = painterResource(trailingIcon),
            contentDescription = "notification",
            Modifier
                .fillMaxHeight()
                .clickable {
                    trailingOnClick()
                },
        )
        Spacer(Modifier.width(12.dp))
    }
}

@Composable
fun TopBarEditable(
    text: MutableState<String>,
    headerIcon: Int =  R.drawable.logo_square,
    headerOnClick: () -> Unit = {},
    trailingIcon: Int,
    trailingOnClick: () -> Unit = {},
    textOnClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .padding(16.dp)
            .defaultBackground()
            .border(1.dp, MaterialTheme.colorScheme.onSurface, DefaultShape)
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(headerIcon), contentDescription = null,
            Modifier
                .fillMaxHeight()
                .clickable {
                    headerOnClick()
                }
                .clip(CircleShape))

        Spacer(Modifier.width(12.dp))
        BasicTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            singleLine = true,
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                ),
            cursorBrush = Brush.linearGradient(
                listOf(
                    MaterialTheme.colorScheme.onBackground,
                    MaterialTheme.colorScheme.onBackground
                )
            )
        )
        Spacer(Modifier.width(12.dp))
        Icon(
            painter = painterResource(trailingIcon),
            contentDescription = "notification",
            Modifier
                .fillMaxHeight()
                .clickable {
                    trailingOnClick()
                },
        )
        Spacer(Modifier.width(12.dp))
    }
}