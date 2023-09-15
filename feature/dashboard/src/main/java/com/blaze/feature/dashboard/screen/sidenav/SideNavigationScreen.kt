package com.blaze.feature.dashboard.screen.sidenav

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.R
import com.blaze.core.ui.components.SideNavigationItem
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.core.utils.util.USER_ID


@Composable
fun SideNavigationScreen(navController: NavController, viewModel: SideNavigationScreenViewModel) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    BackHandler {
        navController.navigate(DashboardRoute.DashboardScreen.route) {
            popUpTo(DashboardRoute.SideNavigationScreen.route) {
                inclusive = true
            }
        }
    }

    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Row(Modifier.padding(bottom = 10.dp), verticalAlignment = Alignment.Bottom) {
            Column {
                Text(text = "Your Name", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = "+91-XXXXXXXXXX")
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.logo_square),
                contentDescription = null,
                Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(5.dp))
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Center
            ) {

            }
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(5.dp))
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Center
            ) {

            }
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(5.dp))
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Center

            ) {

            }
        }

        SideNavigationItem(
            image = R.drawable.baseline_account_circle_24,
            title = "My Account",

            ) {

        }
        SideNavigationItem(
            image = R.drawable.baseline_account_circle_24,
            title = "My Vehicles",

            ) {

        }
        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "My Preference",
            des = "Set search filter options"
        ) {

        }

        SideNavigationItem(
            image = R.drawable.baseline_book_24,
            title = "My Bookings",
        ) {

        }

        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "Charging History",
        ) {

        }



        SideNavigationItem(
            image = R.drawable.baseline_2k, title = "Be a Partner", des = "Join with us"
        ) {
            Toast.makeText(context, "$USER_ID", Toast.LENGTH_SHORT).show()
            navController.navigate(OnBoardingRoute.BoardingStatusScreen.route) {
                popUpTo(DashboardRoute.SideNavigationScreen.route) {
                    inclusive = true
                }
            }
        }

        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "Help & FAQs",
        ) {

        }


        Spacer(modifier = Modifier.weight(1f))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary)
        )
        SideNavigationItem(
            image = R.drawable.baseline_2k, title = "Log Out"
        ) {
            viewModel.firebaseAuth.signOut()
            Toast.makeText(context, "User Log out", Toast.LENGTH_SHORT).show()
            activity.finishAffinity()
        }
    }
}




