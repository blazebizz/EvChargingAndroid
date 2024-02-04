package ai.heart.feature.account.navigation

import ai.heart.feature.account.screen.add_contact.AddContactScreen
import ai.heart.feature.account.screen.add_contact.AddContactViewModel
import ai.heart.feature.account.screen.booking_history.BookingHistoryScreen
import ai.heart.feature.account.screen.booking_history.BookingHistoryViewModel
import ai.heart.feature.account.screen.feedback.FeedbackScreen
import ai.heart.feature.account.screen.feedback.FeedbackViewModel
import ai.heart.feature.account.screen.safety.SafetyScreen
import ai.heart.feature.account.screen.safety.SafetyScreenViewModel
import ai.heart.feature.account.screen.vehicle.VehicleScreen
import ai.heart.feature.account.screen.vehicle.VehicleViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.utils.navigation.AccountRoute


fun NavGraphBuilder.accountNavGraph() {
    composable(route = AccountRoute.SafetyScreen.route) {
        val viewModel = hiltViewModel<SafetyScreenViewModel>()
        SafetyScreen(viewModel)
    }

    composable(route = AccountRoute.AddContactScreen.route) {
        val viewModel = hiltViewModel<AddContactViewModel>()
        AddContactScreen(viewModel)
    }

    composable(route = AccountRoute.FeedbackScreen.route) {
        val viewModel = hiltViewModel<FeedbackViewModel>()
        FeedbackScreen(viewModel)
    }
    composable(route = AccountRoute.VehicleScreen.route) {
        val viewModel = hiltViewModel<VehicleViewModel>()
        VehicleScreen(viewModel)
    }

    composable(route = AccountRoute.BookingHistoryScreen.route) {
        val viewModel = hiltViewModel<BookingHistoryViewModel>()
        BookingHistoryScreen(viewModel)
    }
}