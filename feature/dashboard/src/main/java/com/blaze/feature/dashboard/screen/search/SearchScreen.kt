package com.blaze.feature.dashboard.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.blaze.core.ui.R
import com.blaze.core.utils.util.USER_ID
import com.blaze.feature.dashboard.screen.dashboard.TopBarEditable

@Composable
fun SearchScreen(navController: NavController) {
    val text = remember { mutableStateOf(USER_ID) }
    LaunchedEffect(key1 = text.value) {
        USER_ID = text.value
    }

    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopBarEditable(text = text, trailingIcon = R.drawable.baseline_close_24, trailingOnClick = {
            text.value = ""
        }, headerIcon = R.drawable.arrow_small_left_24, headerOnClick = {
            navController.popBackStack()
        })
    }
}