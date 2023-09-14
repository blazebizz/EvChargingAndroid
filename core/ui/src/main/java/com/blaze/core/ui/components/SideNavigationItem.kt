package com.blaze.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blaze.core.ui.R
import com.blaze.core.ui.ui.theme.SeaSalt

@Composable
fun SideNavigationItem(
    image: Int, title: String, des: String = "", onClick: () -> Unit = {}
) {
    Row(
        Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(top = 6.dp)
            .height(55.dp)
            .background(
                MaterialTheme.colorScheme.secondary, RoundedCornerShape(5.dp)
            )
//            .border(1.dp, color = MarbleWhite, RoundedCornerShape(5.dp))
            .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(5.dp)),
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontSize = 18.sp)
            if (des.isNotEmpty()) Text(text = des)
        }
        Spacer(modifier = Modifier.weight(1f))

        Icon(painter = painterResource(id = R.drawable.arrow_right_24), contentDescription = null)
    }
}