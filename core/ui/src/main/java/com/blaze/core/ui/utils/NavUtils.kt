package com.blaze.core.ui.utils

import androidx.navigation.NavController
import com.blaze.core.utils.navigation.DashboardRoute

fun NavController.navigateCleanNavScreen(route: String){
    this.navigate(route) {
        popUpTo(com.blaze.core.utils.navigation.DashboardRoute.SideNavigationScreen.route) {
            inclusive = true
        }
    }
}
