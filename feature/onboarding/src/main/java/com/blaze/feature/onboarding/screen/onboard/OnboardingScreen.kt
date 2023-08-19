package com.blaze.feature.onboarding.screen.onboard

import android.widget.ProgressBar
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
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun OnBoardingScreen(navController: NavController) {
    val progress by remember { mutableStateOf(1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )

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

        }

        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.weight(3f))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Next")
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(12.dp))
    }
}