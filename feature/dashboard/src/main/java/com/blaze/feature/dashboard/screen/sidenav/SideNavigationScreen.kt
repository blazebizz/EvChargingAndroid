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
import com.blaze.core.utils.navigation.OnBoardingRoute


@Composable
fun SideNavigationScreen(navController: NavController) {
    BackHandler {
        navController.navigate(DashboardRoute.DashboardScreen.route){
            popUpTo(DashboardRoute.SideNavigationScreen.route){
                inclusive = true
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "On Board as Partner", modifier = Modifier.clickable {
            navController.navigate(OnBoardingRoute.OnBoardingScreen.route){
                popUpTo(DashboardRoute.SideNavigationScreen.route){
                    inclusive = true
                }
            }
        })
    }
}