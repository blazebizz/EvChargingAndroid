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
    val progress by remember { mutableStateOf(.51f) }
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
                    .semantics(mergeDescendants = true) {}
                    .padding(10.dp),
                progress = animatedProgress,
            )
        }
        Column(
            Modifier
                .padding(20.dp)
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
        OnBoardingSubScreen.BasicInfoScreen.name -> {
            navController.navigate(OnBoardingSubScreen.DocumentInfoScreen.name) {
                popUpTo(OnBoardingSubScreen.BasicInfoScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.DocumentInfoScreen.name -> {
            navController.navigate(OnBoardingSubScreen.StepThreeScreen.name) {
                popUpTo(OnBoardingSubScreen.DocumentInfoScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.StepThreeScreen.name -> {
            navController.navigate(OnBoardingSubScreen.StepFourScreen.name) {
                popUpTo(OnBoardingSubScreen.StepThreeScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.StepFourScreen.name -> {

        }
    }
}

fun previousFunction(navController: NavHostController, viewModel: OnBoardingViewModel) {
    when (navController.currentDestination?.route) {
        OnBoardingSubScreen.BasicInfoScreen.name -> {

        }

        OnBoardingSubScreen.DocumentInfoScreen.name -> {
            navController.navigate(OnBoardingSubScreen.BasicInfoScreen.name) {
                popUpTo(OnBoardingSubScreen.DocumentInfoScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.StepThreeScreen.name -> {
            navController.navigate(OnBoardingSubScreen.DocumentInfoScreen.name) {
                popUpTo(OnBoardingSubScreen.StepThreeScreen.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.StepFourScreen.name -> {
            navController.navigate(OnBoardingSubScreen.StepThreeScreen.name) {
                popUpTo(OnBoardingSubScreen.StepFourScreen.name) { inclusive = true }
            }
        }
    }
}