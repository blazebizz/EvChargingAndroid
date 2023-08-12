package com.blaze.feature.startup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.feature.startup.screen.addinfo.AdditionalInfoScreen
import com.blaze.feature.startup.screen.login.LoginScreen
import com.blaze.feature.startup.screen.splash.SplashScreen
import com.blaze.feature.startup.screen.signup.SignUpScreen


fun NavGraphBuilder.startUpNavGraph(navController: NavController) {
    composable(route = StartUpRoute.SplashScreen.route) {
        SplashScreen(navController)
    }

    composable(route = StartUpRoute.LoginScreen.route) {
        LoginScreen(navController)
    }

    composable(route = StartUpRoute.SignUpScreen.route) {
        SignUpScreen(navController)
    }
    composable(route = StartUpRoute.AdditionalDetailsScreen.route) {
        AdditionalInfoScreen(navController)
    }
}