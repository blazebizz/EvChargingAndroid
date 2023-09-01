package com.blaze.feature.startup.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.feature.startup.screen.login.LoginScreen
import com.blaze.feature.startup.screen.otp.OtpScreen
import com.blaze.feature.startup.screen.otp.OtpScreenViewModel
import com.blaze.feature.startup.screen.splash.SplashScreen
import com.blaze.feature.startup.screen.splash.SplashScreenViewModel


fun NavGraphBuilder.startUpNavGraph(navController: NavController, coreUi: CoreUiViewModel) {
    composable(route = StartUpRoute.SplashScreen.route) {
        val viewModel = hiltViewModel<SplashScreenViewModel>()
        SplashScreen(navController,viewModel,coreUi)
    }

    composable(route = StartUpRoute.LoginScreen.route) {
        LoginScreen(navController,coreUi)
    }

    composable(route = "${StartUpRoute.MobileOtpScreen.route}/{toSentText}", arguments = listOf(
        navArgument("toSentText") { type = NavType.StringType })
    ) {

        val otpViewModel = hiltViewModel<OtpScreenViewModel>()

        val toSentText = it.arguments!!.getString("toSentText")
        toSentText?.let { it1 -> OtpScreen(navController, it1,otpViewModel,coreUi) }
    }
}