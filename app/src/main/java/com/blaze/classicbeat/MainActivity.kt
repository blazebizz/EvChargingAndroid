package com.blaze.classicbeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.blaze.classicbeat.navigation.SetupNavGraph
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.ui.InitSubUiComponents
import com.blaze.core.ui.ui.theme.ClassicBeatTheme
import com.blaze.core.utils.navigation.StartUpRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClassicBeatTheme {
                // A surface container using the 'background' color from the theme
                val coreUi = hiltViewModel<CoreUiViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navGraphController = rememberNavController()
                    SetupNavGraph(startDestination = StartUpRoute.SplashScreen.route, navController = navGraphController, coreUi = coreUi)
                }

                InitSubUiComponents(coreUi)
            }
        }
    }
}

