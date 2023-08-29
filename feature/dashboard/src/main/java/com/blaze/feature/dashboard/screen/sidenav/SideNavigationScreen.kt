package com.blaze.feature.dashboard.screen.sidenav

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.core.ui.R
import com.blaze.core.utils.navigation.StartUpRoute


@Composable
fun SideNavigationScreen(navController: NavController, viewModel: SideNavigationScreenViewModel) {
    BackHandler {
        navController.navigate(DashboardRoute.DashboardScreen.route){
            popUpTo(DashboardRoute.SideNavigationScreen.route){
                inclusive = true
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "Be a Partner",
            des=""
        ){
            navController.navigate(OnBoardingRoute.OnBoardingScreen.route){
                popUpTo(DashboardRoute.SideNavigationScreen.route){
                    inclusive = true
                }
            }
        }


        SideNavigationItem(
            image = R.drawable.baseline_2k,
            title = "Log Out",
            des=""
        ){
            viewModel.firebaseAuth.signOut()
            navController.navigate(StartUpRoute.SplashScreen.route) {
                popUpTo(DashboardRoute.SideNavigationScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}


@Composable
fun SideNavigationItem(
    image:Int,
    title:String,
    des:String,
    onClick:()->Unit={}
){
    Row (
        Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp)
            .height(100.dp)
            .background(
                Color.LightGray,
                RoundedCornerShape(10.dp)
            )
            .border(2.dp, color = Color.Gray, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
        ){
        Icon(painter = painterResource(id = image), contentDescription = null,
            modifier=Modifier.border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            )
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(text = title, fontSize = 24.sp)
            Text(text = des)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

