package com.blaze.classicbeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.blaze.feature.startup.navigation.startUpNavGraph

@Composable
fun SetupNavGraph(
    startDestination: String, navController: NavHostController
) {
    NavHost(navController = navController,
        startDestination = startDestination
        ){
        startUpNavGraph(navController)
    }
}