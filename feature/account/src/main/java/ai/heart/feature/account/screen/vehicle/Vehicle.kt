package ai.heart.feature.account.screen.vehicle

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.bounceClick
import com.blaze.core.ui.utils.navigateCleanNavScreen
import com.blaze.core.utils.navigation.DashboardRoute


@Composable
fun VehicleScreen(
    navController: NavController,
    viewModel: VehicleViewModel,
    coreVM: CoreViewModel
) {
    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.bounceClick {
                    runCatching {
                        navController.navigateCleanNavScreen(DashboardRoute.DashboardScreen.route)
                    }.getOrElse {
                        navController.popBackStack()
                    }
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = "Vehicle", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))


            Row(
                Modifier
                    .bounceClick {

                    }
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Add",
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
                )
            }
        }

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val errorLottie =
                rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.ride_scooter))
            val progress by animateLottieCompositionAsState(
                composition = errorLottie.value,
                restartOnPlay = true,
                iterations = Int.MAX_VALUE
            )


            // region ui animation
            LottieAnimation(
                composition = errorLottie.value,
                progress = progress,
                modifier = Modifier.size(300.dp)
            )

            Text(text = "Manage your vehicle at single place and share the vehicle with your friends and family.", textAlign = TextAlign.Center)
            Text(text = "1. Unlock more immersive experience by adding your vehicle information!", textAlign = TextAlign.Center)
            Text(text = "2. Share your vehicle with friends and family.", textAlign = TextAlign.Center)
        }

    }
}