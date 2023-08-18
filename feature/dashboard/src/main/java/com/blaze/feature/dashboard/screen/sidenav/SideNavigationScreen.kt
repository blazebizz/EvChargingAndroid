package com.blaze.feature.dashboard.screen.sidenav

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.DashboardRoute


@Composable
fun SideNavigationScreen(navController: NavController) {
    BackHandler {
        navController.popBackStack()
        navController.clearBackStack(DashboardRoute.SideNavigationScreen.route)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "On Board as Partner", modifier = Modifier.clickable {

        })
    }
}