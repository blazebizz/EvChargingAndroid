package com.blaze.core.utils.navigation

sealed class NavigationRoute(val route : String){
    object Auth : NavigationRoute(route ="Auth" )
    object OnBoarding : NavigationRoute(route = "OnBoarding")
    object Dashboard : NavigationRoute(route = "Dashboard")
}

sealed class StartUpRoute(val route : String){
    object LoginScreen : NavigationRoute(route ="LoginScreen" )
    object SplashScreen : NavigationRoute(route = "SplashScreen")
    object SignUpScreen : NavigationRoute(route = "SignUpScreen")
    object ForgotPasswordScreen : NavigationRoute(route = "ForgotPasswordScreen")
    object AdditionalDetailsScreen : NavigationRoute(route = "AdditionalDetailsScreen")
    object MobileOtpScreen : NavigationRoute(route = "MobileOtpScreen")
}

sealed class OnBoardingRoute(val route : String){
    object OnBoardingScreen : NavigationRoute(route =   "OnBoardingScreen")
}