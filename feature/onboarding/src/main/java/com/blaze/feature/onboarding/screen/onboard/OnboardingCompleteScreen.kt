package com.blaze.feature.onboarding.screen.onboard
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.navigation.LocalCore
import com.blaze.core.ui.navigation.LocalNavigation
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.OnBoardingRoute


@Composable
fun OnBoardingCompleteScreen(

) {

    val navController = LocalNavigation.current
    CompleteScreen {
        navController.navigate(DashboardRoute.DashboardScreen.route) {
            popUpTo(OnBoardingRoute.BoardingCompleteScreen.route) {
                inclusive = true
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CompleteScreen(
    onBack: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo_square), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Request Received\nVerification in Progress",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Thank you for your request. We've received it and will now begin the process of verifying your details. Please allow us some time to onboard you.",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(text = "Return to Dashboard", onClick = onBack)
        Spacer(modifier = Modifier.weight(1f))
    }
}