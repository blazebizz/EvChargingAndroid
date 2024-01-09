package com.blaze.core.utils.navigation

sealed class NavigationRoute(val route: String) {
    object Auth : NavigationRoute(route = "Auth")
    object OnBoarding : NavigationRoute(route = "OnBoarding")
    object Dashboard : NavigationRoute(route = "Dashboard")
}

sealed class StartUpRoute(val route: String) {
    object LoginScreen : NavigationRoute(route = "LoginScreen")
    object SplashScreen : NavigationRoute(route = "SplashScreen")
    object CreateUserScreen : NavigationRoute(route = "CreateUserScreen")
    object MobileOtpScreen : NavigationRoute(route = "MobileOtpScreen")
}

sealed class OnBoardingRoute(val route: String) {
    object OnBoardingScreen : NavigationRoute(route = "OnBoardingScreen")
    object BoardingCompleteScreen : NavigationRoute(route = "BoardingCompleteScreen")
    object BoardingStatusScreen : NavigationRoute(route = "BoardingStatusScreen")
}

sealed class DashboardRoute(val route: String) {
    object DashboardScreen : NavigationRoute(route = "DashboardScreen")
    object SearchScreen : NavigationRoute(route = "SearchScreen")
    object SideNavigationScreen : NavigationRoute(route = "SideNavigationScreen")
}

sealed class AccountRoute(val route: String) {
    object SafetyScreen : NavigationRoute(route = "SafetyScreen")
    object FeedbackScreen : NavigationRoute(route = "FeedbackScreen")
    object AddContactScreen : NavigationRoute(route = "AddContactScreen")
    object VehicleScreen : NavigationRoute(route = "VehicleScreen")
}
