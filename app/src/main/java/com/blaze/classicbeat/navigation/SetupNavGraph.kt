package com.blaze.classicbeat.navigation

import ai.heart.feature.account.navigation.accountNavGraph
import ai.heart.feature.booking.navigation.bookingNavGraph
import ai.heart.feature.station_setup.navigation.stationSetUpNavGraph
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.blaze.core.ui.navigation.LocalCore
import com.blaze.core.ui.navigation.LocalNavigation
import com.blaze.feature.dashboard.navigation.dashboardNavGraph
import com.blaze.feature.onboarding.navigation.onBoardingNavGraph
import com.blaze.feature.onboarding.screen.OnBoardingViewModel
import com.blaze.feature.startup.navigation.startUpNavGraph


@Composable
fun SetupNavGraph(
    startDestination: String,
    onBoardingViewModel: OnBoardingViewModel
) {
    NavHost(
        navController =  LocalNavigation.current, startDestination = startDestination
    ) {
        startUpNavGraph()
        dashboardNavGraph()
        onBoardingNavGraph(onBoardingViewModel)
        accountNavGraph()
        stationSetUpNavGraph()
        bookingNavGraph()
    }
}

