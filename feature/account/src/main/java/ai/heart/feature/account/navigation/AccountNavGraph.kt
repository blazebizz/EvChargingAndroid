package ai.heart.feature.account.navigation

import ai.heart.feature.account.screen.add_contact.AddContactScreen
import ai.heart.feature.account.screen.add_contact.AddContactViewModel
import ai.heart.feature.account.screen.safety.SafetyScreen
import ai.heart.feature.account.screen.safety.SafetyScreenViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.utils.navigation.AccountRoute


fun NavGraphBuilder.accountNavGraph(navController: NavController, coreVM: CoreViewModel) {
    composable(route = AccountRoute.SafetyScreen.route) {
        val viewModel = hiltViewModel<SafetyScreenViewModel>()
        SafetyScreen(navController, coreVM, viewModel)
    }

    composable(route = AccountRoute.AddContactScreen.route) {
        val viewModel = hiltViewModel<AddContactViewModel>()
        AddContactScreen(navController, viewModel, coreVM)
    }
}