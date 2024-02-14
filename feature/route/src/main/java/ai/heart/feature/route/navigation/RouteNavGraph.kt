package ai.heart.feature.route.navigation

import ai.heart.feature.route.screen.route.RouteScreen
import ai.heart.feature.route.screen.route.RouteViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.utils.navigation.RouteRoute


fun NavGraphBuilder.routeNavGraph() {
    composable(route = RouteRoute.RouteScreen.route) {
        val viewModel = hiltViewModel<RouteViewModel>()
        RouteScreen(viewModel)
    }
}