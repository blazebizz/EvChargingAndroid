package com.blaze.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blaze.core.ui.DefaultShape
import com.blaze.core.ui.R
import com.blaze.core.ui.defaultBackground


@Composable
@Preview(showBackground = true)
fun PreviewComponent() {

    Column {
        TopBar(
            text = "", trailingIcon = R.drawable.location_pin
        )
        val text = remember { mutableStateOf("j") }

        TopBarEditable(
            text = text, trailingIcon = R.drawable.location_pin
        )

        ToolBar(title = "Preview")

    }
}

@Composable
fun ToolBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackPress: () -> Unit = {},
    trailingContent: @Composable BoxScope.() -> Unit = {}
) {
    Row(
        modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.bounceClick(onBackPress)
        )
        Spacer(Modifier.width(12.dp))
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Box(
            Modifier.align(Alignment.CenterVertically)
        ) {
            trailingContent()
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    headerIcon: Int = R.drawable.logo_square,
    headerOnClick: () -> Unit = {},
    trailingIcon: Int,
    trailingOnClick: () -> Unit = {},
    textOnClick: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .padding(16.dp)
            .shadow(4.dp, shape = DefaultShape)
            .defaultBackground()

//            .border(1.dp,MaterialTheme.colorScheme.onBackground, DefaultShape)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 50.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(headerIcon),
            contentDescription = null,
            Modifier
                .size(30.dp)
                .bounceClick {
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
                .size(25.dp)
                .clickable {
                    trailingOnClick()
                },
        )
        Spacer(Modifier.width(12.dp))
    }
}


@Composable
fun TopBarEditable(
    modifier: Modifier = Modifier,
    text: MutableState<String>,
    onValueChange: (String) -> Unit = {},
    headerIcon: Int = R.drawable.logo_square,
    headerOnClick: () -> Unit = {},
    trailingIcon: Int,
    trailingOnClick: () -> Unit = {},
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
        Image(painter = painterResource(headerIcon),
            contentDescription = null,
            Modifier
                .fillMaxHeight()
                .clickable {
                    headerOnClick()
                }
                .clip(CircleShape))

        Spacer(Modifier.width(12.dp))
        BasicTextField(
            value = text.value, onValueChange = {
                text.value = it
                onValueChange(it)
            }, singleLine = true, modifier = Modifier.weight(1f), textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
            ), cursorBrush = Brush.linearGradient(
                listOf(
                    MaterialTheme.colorScheme.onBackground, MaterialTheme.colorScheme.onBackground
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