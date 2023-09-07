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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.blaze.core.ui.ui.theme.SeaSalt
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.OnBoardingRoute


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
                painter = painterResource(id = R.drawable.logo_square), contentDescription = null,
                Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )
        }

        Column(
            Modifier.fillMaxWidth()
                .height(120.dp)
                ) {

           Box(modifier = Modifier.padding(5.dp).background(SeaSalt, RoundedCornerShape(5.dp)).fillMaxHeight().weight(1f))
        }

        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "Be a Partner",
            des = "des"
        ) {
            navController.navigate(OnBoardingRoute.OnBoardingScreen.route) {
                popUpTo(DashboardRoute.SideNavigationScreen.route) {
                    inclusive = true
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "Log Out"
        ) {
            viewModel.firebaseAuth.signOut()
            Toast.makeText(context, "User Log out", Toast.LENGTH_SHORT).show()
            activity.finishAffinity()
        }
    }
}


@Composable
fun SideNavigationItem(
    image: Int,
    title: String,
    des: String= "",
    onClick: () -> Unit = {}
) {
    Row(
        Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(top = 6.dp)
            .height(55.dp)
            .background(
                SeaSalt,
                RoundedCornerShape(5.dp)
            )
//            .border(1.dp, color = MarbleWhite, RoundedCornerShape(5.dp))
            .padding(5.dp),
    ) {
        Icon(
            painter = painterResource(id = image), contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(5.dp))

        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            Modifier
                .fillMaxHeight()
                .align(CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontSize = 18.sp)
            if (des.isNotEmpty()) Text(text = des)
        }
    }
}

