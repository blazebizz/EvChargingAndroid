package ai.heart.feature.booking.navigation

import ai.heart.feature.booking.screen.booking_status.BookingStatusScreen
import ai.heart.feature.booking.screen.charging.ChargingScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.utils.navigation.BookingRoute


fun NavGraphBuilder.bookingNavGraph() {
    composable(route = BookingRoute.ChargingScreen.route) {
        ChargingScreen()
    }

    composable(route = BookingRoute.BookingStatusScreen.route) {
        BookingStatusScreen()
    }
}