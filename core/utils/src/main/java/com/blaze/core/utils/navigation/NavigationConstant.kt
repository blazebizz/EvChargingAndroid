package com.blaze.core.utils.navigation

sealed class NavigationRoute(val route: String) {
    data object Auth : NavigationRoute(route = "Auth")
    data object OnBoarding : NavigationRoute(route = "OnBoarding")
    data object Dashboard : NavigationRoute(route = "Dashboard")
}

sealed class StartUpRoute(val route: String) {
    data object LoginScreen : StartUpRoute(route = "LoginScreen")
    data object SplashScreen : StartUpRoute(route = "SplashScreen")
    data object CreateUserScreen : StartUpRoute(route = "CreateUserScreen")
    data object MobileOtpScreen : StartUpRoute(route = "MobileOtpScreen")
}

sealed class OnBoardingRoute(val route: String) {
    data object OnBoardingScreen : OnBoardingRoute(route = "OnBoardingScreen")
    data object BoardingCompleteScreen : OnBoardingRoute(route = "BoardingCompleteScreen")
    data object BoardingStatusScreen : OnBoardingRoute(route = "BoardingStatusScreen")
}

sealed class DashboardRoute(val route: String) {
    data object DashboardScreen : DashboardRoute(route = "DashboardScreen")
    data object SearchScreen : DashboardRoute(route = "SearchScreen")
    data object SideNavigationScreen : DashboardRoute(route = "SideNavigationScreen")
}

sealed class AccountRoute(val route: String) {
    data object SafetyScreen : AccountRoute(route = "SafetyScreen")
    data object FeedbackScreen : AccountRoute(route = "FeedbackScreen")
    data object AddContactScreen : AccountRoute(route = "AddContactScreen")
    data object VehicleScreen : AccountRoute(route = "VehicleScreen")
    data object BookingHistoryScreen : AccountRoute(route = "BookingHistoryScreen")
}

sealed class StationSetUpRoute(val route: String) {
    data object StationSetUpScreen : StationSetUpRoute(route = "StationSetUpScreen")
}

sealed class BookingRoute(val route: String) {
    data object ChargingScreen : StationSetUpRoute(route = "ChargingScreen")
}
