package com.blaze.feature.dashboard.screen.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.blaze.core.ui.R


@Composable
fun DashboardMapContent(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.map),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() })
    }
}