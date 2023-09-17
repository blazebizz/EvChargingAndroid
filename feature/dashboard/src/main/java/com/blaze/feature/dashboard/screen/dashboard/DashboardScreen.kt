package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.blaze.core.ui.R
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.util.USER_ID

@Composable
fun DashboardScreen(navController: NavController) {
    val activity = LocalContext.current as Activity

    BackHandler {
        activity.finishAffinity()
    }
    Box(
        Modifier.fillMaxSize()
    ) {

        MapView(Modifier)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
        ) {
            Box(
                Modifier.padding(it)
            ) {
                TopBar(headerIcon = R.drawable.logo_square,
                    trailingIcon = R.drawable.search_24,
                    headerOnClick = {
                        navController.navigate(DashboardRoute.SideNavigationScreen.route)
                    },
                    textOnClick = {
                        navController.navigate(DashboardRoute.SearchScreen.route)
                    })

            }


        }
    }


}

@Composable
fun MapView(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}