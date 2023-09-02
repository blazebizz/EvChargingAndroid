package com.blaze.feature.onboarding.screen.onboard

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.OutlinedButton
import com.blaze.core.ui.ui.theme.BabyPowderWhite
import com.blaze.feature.onboarding.navigation.OnBoardingSubNavGraph
import com.blaze.feature.onboarding.navigation.OnBoardingSubScreen
import com.blaze.feature.onboarding.screen.OnBoardingViewModel


@Composable
fun OnBoardingScreen(navController: NavController) {
    val viewModel = hiltViewModel<OnBoardingViewModel>()
    val progress by remember { mutableFloatStateOf(.51f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    val onBoardingNavController = rememberNavController()

    BackHandler {
        previousFunction(onBoardingNavController,viewModel,navController)
    }

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .padding(start = 16.dp, top = 10.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back", Modifier.clickable {
                navController.popBackStack()
            })
        }

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            RoundedTextIndicator("1")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {}
                ,
                progress = animatedProgress,color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("2")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = animatedProgress,color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("3")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = animatedProgress,color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("4")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = animatedProgress,color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("5")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = animatedProgress,
                color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("")
            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            Modifier
                .padding(10.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(BabyPowderWhite)
                .padding(10.dp)
        ) {
            OnBoardingSubNavGraph(onBoardingNavController,viewModel)
        }

        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton("Previous") {
                previousFunction(onBoardingNavController, viewModel, navController)
            }
            Spacer(modifier = Modifier.weight(3f))

            Button(text = "Continue") {
                nextFunction(onBoardingNavController, viewModel)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
    }
}

fun nextFunction(navController: NavHostController, viewModel: OnBoardingViewModel) {
    when (navController.currentDestination?.route) {
        OnBoardingSubScreen.Page1.name -> {
            navController.navigate(OnBoardingSubScreen.Page2.name) {
                popUpTo(OnBoardingSubScreen.Page1.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page2.name -> {
            navController.navigate(OnBoardingSubScreen.Page3.name) {
                popUpTo(OnBoardingSubScreen.Page2.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page3.name -> {
            navController.navigate(OnBoardingSubScreen.Page4.name) {
                popUpTo(OnBoardingSubScreen.Page3.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page4.name -> {
            navController.navigate(OnBoardingSubScreen.Page5.name) {
                popUpTo(OnBoardingSubScreen.Page4.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page5.name -> {
            navController.navigate(OnBoardingSubScreen.OnBoardingTermsScreen.name) {
                popUpTo(OnBoardingSubScreen.Page5.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingTermsScreen.name -> {

        }
    }
}

fun previousFunction(
    subNavController: NavHostController,
    viewModel: OnBoardingViewModel,
    mainNavController: NavController
) {
    when (subNavController.currentDestination?.route) {
        OnBoardingSubScreen.Page1.name -> {
            mainNavController.popBackStack()
        }

        OnBoardingSubScreen.Page2.name -> {
            subNavController.navigate(OnBoardingSubScreen.Page1.name) {
                popUpTo(OnBoardingSubScreen.Page2.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page3.name -> {
            subNavController.navigate(OnBoardingSubScreen.Page2.name) {
                popUpTo(OnBoardingSubScreen.Page3.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page4.name -> {
            subNavController.navigate(OnBoardingSubScreen.Page3.name) {
                popUpTo(OnBoardingSubScreen.Page4.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page5.name -> {
            subNavController.navigate(OnBoardingSubScreen.Page4.name) {
                popUpTo(OnBoardingSubScreen.Page5.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.OnBoardingTermsScreen.name -> {
            subNavController.navigate(OnBoardingSubScreen.Page5.name) {
                popUpTo(OnBoardingSubScreen.OnBoardingTermsScreen.name) { inclusive = true }
            }
        }

    }
}

@Composable
fun RoundedTextIndicator(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .size(25.dp)
            .background(MaterialTheme.colorScheme.background, shape = CircleShape)
            .border(
                2.dp, MaterialTheme.colorScheme.onBackground, CircleShape
            )
            .padding(3.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}