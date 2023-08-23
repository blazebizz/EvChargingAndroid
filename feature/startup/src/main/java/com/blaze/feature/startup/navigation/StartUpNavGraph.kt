package com.blaze.feature.startup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.feature.startup.screen.addinfo.AdditionalInfoScreen
import com.blaze.feature.startup.screen.forgetpassword.ForgetPasswordScreen
import com.blaze.feature.startup.screen.login.LoginScreen
import com.blaze.feature.startup.screen.otp.OtpScreen
import com.blaze.feature.startup.screen.splash.SplashScreen
import com.blaze.feature.startup.screen.signup.SignUpScreen


fun NavGraphBuilder.startUpNavGraph(navController: NavController) {
    composable(route = StartUpRoute.SplashScreen.route) {
        SplashScreen(navController)
    }

    composable(route = StartUpRoute.LoginScreen.route) {
        LoginScreen(navController)
    }

    composable(route = "${StartUpRoute.MobileOtpScreen.route}/{toSentText}", arguments = listOf(
        navArgument("toSentText") { type = NavType.StringType })
    ) {
        val toSentText = it.arguments!!.getString("toSentText")
        toSentText?.let { it1 -> OtpScreen(navController, it1) }
    }
}