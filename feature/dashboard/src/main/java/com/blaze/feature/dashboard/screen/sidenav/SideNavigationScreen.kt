package com.blaze.feature.dashboard.screen.sidenav

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.core.ui.R
import com.blaze.core.ui.ui.theme.LightGray
import com.blaze.core.ui.ui.theme.MarbleWhite
import com.blaze.core.utils.navigation.StartUpRoute


@Composable
fun SideNavigationScreen(navController: NavController, viewModel: SideNavigationScreenViewModel) {
  val context = LocalContext.current
  val activity = LocalContext.current as Activity
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
            des="des"
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
            Toast.makeText(context, "User Log out", Toast.LENGTH_SHORT).show()
           activity.finishAffinity()
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
            .padding(top = 6.dp)
            .height(65.dp)
            .background(
                MarbleWhite,
                RoundedCornerShape(10.dp)
            )
            .border(2.dp, color = LightGray, RoundedCornerShape(10.dp))
            .padding(10.dp)
        ,
        ){
        Icon(painter = painterResource(id = image), contentDescription = null,
            modifier= Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .border(1.dp, LightGray, RoundedCornerShape(10.dp))
            )
        Spacer(modifier = Modifier.width(10.dp))
        Column (
            Modifier
                .fillMaxHeight()
               ){
            Spacer(modifier = Modifier.weight(1f))
            Text(text = title, fontSize = 24.sp)
            Text(text = des)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

