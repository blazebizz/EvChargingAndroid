package com.blaze.feature.startup.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.feature.startup.screen.createuser.CreateUserScreen
import com.blaze.feature.startup.screen.createuser.CreateUserViewModel
import com.blaze.feature.startup.screen.login.LoginScreen
import com.blaze.feature.startup.screen.otp.OtpScreen
import com.blaze.feature.startup.screen.otp.OtpScreenViewModel
import com.blaze.feature.startup.screen.splash.SplashScreen
import com.blaze.feature.startup.screen.splash.SplashScreenViewModel




fun NavGraphBuilder.startUpNavGraph() {

    composable(route = StartUpRoute.SplashScreen.route) {
        val viewModel = hiltViewModel<SplashScreenViewModel>()
        SplashScreen(viewModel)
    }

    composable(route = StartUpRoute.LoginScreen.route) {
        LoginScreen()
    }

    composable(route = "${StartUpRoute.MobileOtpScreen.route}/{toSentText}", arguments = listOf(
        navArgument("toSentText") { type = NavType.StringType })
    ) {

        val otpViewModel = hiltViewModel<OtpScreenViewModel>()

        val toSentText = it.arguments?.getString("toSentText")
        toSentText?.let { it1 -> OtpScreen( it1,otpViewModel,) }
    }

    composable(route = "${StartUpRoute.CreateUserScreen.route}/{stdCode}/{mobileNumber}/{uid}",
        arguments = listOf(
            navArgument("mobileNumber"){type = NavType.StringType}
        )
        ){
        val viewModel = hiltViewModel<CreateUserViewModel>()
        val uid = it.arguments?.getString("uid")
        val stdCode = it.arguments?.getString("stdCode")
        val mobileNumber = it.arguments?.getString("mobileNumber")

        CreateUserScreen(viewModel,stdCode,mobileNumber,uid)
    }
}