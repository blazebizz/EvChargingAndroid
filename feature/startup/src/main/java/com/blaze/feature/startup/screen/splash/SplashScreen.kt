package com.blaze.feature.startup.screen.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.navigation.StartUpRoute
import com.blaze.data.startup.model.req.GenerateTokenRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel,
    coreVm: CoreViewModel
) {
    //    //region status color
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = MaterialTheme.colorScheme.primary)
    systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.primary)
//    //endregion
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            if (viewModel.firebaseAuth.currentUser != null) {
                viewModel.generateToken(
                    body= GenerateTokenRequest(
                        userId = viewModel.firebaseAuth.currentUser?.uid
                    ),
                    onSuccess = {
                        coreVm.currentUserNumber.value = viewModel.firebaseAuth.currentUser?.phoneNumber ?: ""
                        coreVm.currentUserType.value = it.data?.userType?:""
                        coreVm.currentUserName.value = it.data?.name?:""

                         navController.navigate(
                            DashboardRoute.DashboardScreen.route,
                            navOptions = NavOptions.Builder() //this will remove this screen after it navigate to next screen
                                .setPopUpTo(navController.graph.startDestinationId, inclusive = true)
                                .build()
                        )
                    },
                    onFailure = {msg,obj->
                        if (msg == "Invalid User ID"){
                            val toSentText = viewModel.firebaseAuth.currentUser?.phoneNumber?:""
                            navController.navigate("${StartUpRoute.CreateUserScreen.route}/${toSentText.dropLast(10)}/${toSentText.takeLast(10)}/${viewModel.firebaseAuth.currentUser?.uid?:""}") {
                                popUpTo(StartUpRoute.SplashScreen.route) {
                                    inclusive = true
                                }
                            }
                        }else{
                            coreVm.snackbar(msg)
                        }
                    },
                    loading = coreVm.loading
                )


            } else {
                navController.navigate(
                    StartUpRoute.LoginScreen.route,
                    navOptions = NavOptions.Builder() //this will remove this screen after it navigate to next screen
                        .setPopUpTo(navController.graph.startDestinationId, inclusive = true)
                        .build()
                )
            }
        }
    }

    Slplashh()
}

@Composable
@Preview
fun Slplashh() {
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "",
            Modifier
                .fillMaxWidth(0.5f)
                .align(CenterHorizontally),
        )
        Spacer(modifier = Modifier.weight(2f))

//            Text(
//                text = "Power Up Your Drive with",
//                fontSize = 30.sp,
//                color = MaterialTheme.colorScheme.onBackground,
//                fontWeight = FontWeight.SemiBold
//            )
//            Text(
//                text = "Random",
//                fontSize = 35.sp,
//                color = MaterialTheme.colorScheme.background,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.weight(6f))
    }

}
