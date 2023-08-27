package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaze.feature.onboarding.navigation.OnBoardingSubNavGraph
import com.blaze.feature.onboarding.navigation.OnBoardingSubScreen
import com.blaze.feature.onboarding.screen.OnBoardingViewModel


@Composable
fun OnBoardingScreen(navController: NavController) {
    val context = LocalContext.current

    val viewModel = hiltViewModel<OnBoardingViewModel>()
    val progress by remember { mutableFloatStateOf(.51f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    val onBoardingNavController = rememberNavController()






    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth()) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back", Modifier.clickable {
                navController.popBackStack()
            })
        }

        Row(Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {}
                    .padding(10.dp),
                progress = animatedProgress,
            )
            Spacer(modifier = Modifier.weight(1f))
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {}
                    .padding(10.dp),
                progress = animatedProgress,
            )
            Spacer(modifier = Modifier.weight(1f))
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {}
                    .padding(10.dp),
                progress = animatedProgress,
            )
            Spacer(modifier = Modifier.weight(1f))
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {}
                    .padding(10.dp),
                progress = animatedProgress,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Column(
            Modifier
                .padding(10.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            OnBoardingSubNavGraph(onBoardingNavController)
        }

        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = {
                    previousFunction(onBoardingNavController, viewModel)
                }
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.weight(3f))
            Button(onClick = {
                nextFunction(onBoardingNavController, viewModel)
            }) {
                Text(text = "Continue")
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(12.dp))
    }
}

fun nextFunction(navController: NavHostController, viewModel: OnBoardingViewModel) {
    when (navController.currentDestination?.route) {
        OnBoardingSubScreen.OnBoardVehicleSelectionScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingInfoScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardVehicleSelectionScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingInfoScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingUploadDocScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingInfoScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingUploadDocScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingParkingAreaScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingUploadDocScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingParkingAreaScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingTermsScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingParkingAreaScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingTermsScreen.name -> {

        }
    }
}

fun previousFunction(navController: NavHostController, viewModel: OnBoardingViewModel) {
    when (navController.currentDestination?.route) {
        OnBoardingSubScreen.OnBoardVehicleSelectionScreen.name -> {

        }

        OnBoardingSubScreen.OnBoardingInfoScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardVehicleSelectionScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingInfoScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingUploadDocScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingInfoScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingUploadDocScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingParkingAreaScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingUploadDocScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingParkingAreaScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingTermsScreen.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingParkingAreaScreen.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingTermsScreen.name) { inclusive = true }
            }
        }
    }
}