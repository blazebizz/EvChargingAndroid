package com.blaze.feature.startup.screen.splash

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.blaze.core.ui.ui.theme.PrimaryColor
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.core.ui.R
import com.blaze.core.ui.ui.theme.BoardBlack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashScreenViewModel) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            if (viewModel.firebaseAuth.currentUser != null) {
                Toast.makeText(context, "Already Logged In", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    DashboardRoute.DashboardScreen.route,
                    navOptions = NavOptions.Builder() //this will remove this screen after it navigate to next screen
                        .setPopUpTo(navController.graph.startDestinationId, inclusive = true)
                        .build()
                )
            } else {
                navController.navigate(
                    StartUpRoute.LoginScreen.route,
                    navOptions = NavOptions.Builder() //this will remove this screen after it navigate to next screen
                        .setPopUpTo(navController.graph.startDestinationId, inclusive = true)
                        .build()
                )
            }
        }
    }

    Slplashh()
}

@Composable
@Preview
fun Slplashh() {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.young_bg), contentDescription = "",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Column(Modifier) {
                Text(
                    text = "Power Up Your Drive with",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "EvoCharge",
                    fontSize = 35.sp,
                    color = PrimaryColor,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}



