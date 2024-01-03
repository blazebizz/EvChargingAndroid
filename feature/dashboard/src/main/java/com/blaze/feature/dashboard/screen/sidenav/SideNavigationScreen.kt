package com.blaze.feature.dashboard.screen.sidenav

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.SideNavigationItem
import com.blaze.core.ui.components.pressClick
import com.blaze.core.ui.defaultBackground
import com.blaze.core.ui.utils.navigateCleanNavScreen
import com.blaze.core.utils.navigation.AccountRoute
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.core.utils.navigation.StartUpRoute


/*@Composable
@Preview
fun SideNavigationScreenPreview(){
    val nav = rememberNavController()
    val vie = hiltViewModel<SideNavigationScreenViewModel>()
    SideNavigationScreen(navController = nav, viewModel = vie)
}*/

@Composable
fun SideNavigationScreen(
    navController: NavController,
    viewModel: SideNavigationScreenViewModel,
    coreUi: CoreViewModel
) {
    val context = LocalContext.current
    BackHandler {
        navController.navigateCleanNavScreen(DashboardRoute.DashboardScreen.route)

    }

    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Row(Modifier.padding(bottom = 10.dp), verticalAlignment = Alignment.Bottom) {
            Image(
                painter = painterResource(id = R.drawable.logo_square),
                contentDescription = null,
                Modifier
                    .padding(end = 16.dp)
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(text = coreUi.currentUserName.value, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = coreUi.currentUserNumber.value)
            }
            Spacer(modifier = Modifier.weight(1f))
          Icon(Icons.Default.Close, contentDescription = null,modifier=Modifier.pressClick {
              navController.navigateCleanNavScreen(DashboardRoute.DashboardScreen.route)
          })
        }

        Row(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                    .defaultBackground()
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Center
            ) {

            }
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .defaultBackground()
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Center
            ) {

            }
            Box(
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .defaultBackground()
                    .fillMaxHeight()
                    .weight(1f), contentAlignment = Center

            ) {

            }
        }

//        SideNavigationItem(
//            image = R.drawable.baseline_account_circle_24,
//            title = "My Account",
//
//            ) {
//
//        }
        SideNavigationItem(
            image = R.drawable.hourglass_end_24,
            title = "Safety",
            des= "Your safety precursion & Emergency contact details",
        ) {
            navController.navigateCleanNavScreen(AccountRoute.SafetyScreen.route)
        }

        SideNavigationItem(
            image = R.drawable.garage_car_24,
            title = "My Vehicle",
            des = "Save your vehicle details for better experience"
            ) {

        }
//        SideNavigationItem(
//            image = R.drawable.chart_network_24,
//            title = "My Preference",
//            des = "Set search filter options"
//        ) {
//
//        }


  SideNavigationItem(
            image = R.drawable.hourglass_end_24,
            title = "My Booking",
        ) {

        }

        SideNavigationItem(
            image = R.drawable.dashboard_24,
            title = "Charging History",
        ) {

        }


        SideNavigationItem(
            image = R.drawable.users_24, title = "Become a Partner",
//            des = "Join with us"
        ) {
//            Toast.makeText(context, "$USER_ID", Toast.LENGTH_SHORT).show()
            navController.navigateCleanNavScreen(OnBoardingRoute.BoardingStatusScreen.route)
        }

        SideNavigationItem(
            image = R.drawable.sparkles_24,
            title = "Payment",
        ) {

        }

        SideNavigationItem(
            image = R.drawable.sparkles_24,
            title = "Refer and Earn",
        ) {

        }

        SideNavigationItem(
            image = R.drawable.sparkles_24,
            title = "Feedback",
        ) {
            navController.navigateCleanNavScreen(AccountRoute.FeedbackScreen.route)
        }

        SideNavigationItem(
            image = R.drawable.sparkles_24,
            title = "Notification",
        ) {

        }



        SideNavigationItem(
            image = R.drawable.sparkles_24,
            title = "Help & FAQs",
        ) {

        }

        Spacer(modifier = Modifier.weight(1f))

        SideNavigationItem(
            image = R.drawable.baseline_2k, title = "Log Out"
        ) {
            viewModel.firebaseAuth.signOut()
            Toast.makeText(context, "User Log out", Toast.LENGTH_SHORT).show()
            navController.navigateCleanNavScreen(StartUpRoute.SplashScreen.route)
        }
    }
}




