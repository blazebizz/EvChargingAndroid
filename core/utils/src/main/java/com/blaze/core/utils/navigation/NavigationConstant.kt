package com.blaze.core.utils.navigation

sealed class NavigationRoute(val route: String) {
    object Auth : NavigationRoute(route = "Auth")
    object OnBoarding : NavigationRoute(route = "OnBoarding")
    object Dashboard : NavigationRoute(route = "Dashboard")
}

sealed class StartUpRoute(val route: String) {
    object LoginScreen : StartUpRoute(route = "LoginScreen")
    object SplashScreen : StartUpRoute(route = "SplashScreen")
    object CreateUserScreen : StartUpRoute(route = "CreateUserScreen")
    object MobileOtpScreen : StartUpRoute(route = "MobileOtpScreen")
}

sealed class OnBoardingRoute(val route: String) {
    object OnBoardingScreen : OnBoardingRoute(route = "OnBoardingScreen")
    object BoardingCompleteScreen : OnBoardingRoute(route = "BoardingCompleteScreen")
    object BoardingStatusScreen : OnBoardingRoute(route = "BoardingStatusScreen")
}

sealed class DashboardRoute(val route: String) {
    object DashboardScreen : DashboardRoute(route = "DashboardScreen")
    object SearchScreen : DashboardRoute(route = "SearchScreen")
    object SideNavigationScreen : DashboardRoute(route = "SideNavigationScreen")
}

sealed class AccountRoute(val route: String) {
    object SafetyScreen : AccountRoute(route = "SafetyScreen")
    object FeedbackScreen : AccountRoute(route = "FeedbackScreen")
    object AddContactScreen : AccountRoute(route = "AddContactScreen")
    object VehicleScreen : AccountRoute(route = "VehicleScreen")
    object BookingHistoryScreen : AccountRoute(route = "BookingHistoryScreen")
}

sealed class StationSetUpRoute(val route: String) {
    object StationSetUpScreen : StationSetUpRoute(route = "StationSetUpScreen")
}
