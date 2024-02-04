package ai.heart.feature.station_setup.navigation

import ai.heart.feature.station_setup.screen.StationScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blaze.core.utils.navigation.StationSetUpRoute


fun NavGraphBuilder.stationSetUpNavGraph() {
    composable(route = StationSetUpRoute.StationSetUpScreen.route) {
        StationScreen()
    }
}