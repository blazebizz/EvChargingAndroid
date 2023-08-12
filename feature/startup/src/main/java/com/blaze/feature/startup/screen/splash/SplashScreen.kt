package com.blaze.feature.startup.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.blaze.core.utils.navigation.StartUpRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            navController.navigate(StartUpRoute.LoginScreen.route,
                navOptions =  NavOptions.Builder() //this will remove this screen after it navigate to next screen
                    .setPopUpTo(navController.graph.startDestinationId, inclusive = true)
                    .build()
                )
        }
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
        ){
        Text(text = "This is a Splash Screen")
    }
}