package com.blaze.feature.dashboard.screen.dashboard

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.R
import com.blaze.core.ui.components.OutlinedTextField
import com.blaze.core.ui.components.pressClick
import com.blaze.core.ui.ui.theme.ClassicBeatTheme
import com.blaze.core.utils.navigation.DashboardRoute
import com.blaze.core.utils.util.USER_ID

@Composable
fun DashboardScreen(navController: NavController) {
    val activity = LocalContext.current as Activity
    val value = remember { mutableStateOf("") }
    LaunchedEffect(key1 = value.value){
        USER_ID = value.value
    }
    BackHandler {
        activity.finishAffinity()
    }
    ClassicBeatTheme {
        Scaffold(
            topBar = {
                Row(
                    Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    verticalAlignment = CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.logo_square),
                        contentDescription = null,
                        Modifier
                            .clickable {
                                navController.navigate(DashboardRoute.SideNavigationScreen.route)
                            }
                            .size(40.dp)
                            .clip(CircleShape)
                            .align(CenterVertically))

                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Random",
                        modifier = Modifier
                            .height(30.dp)
                            .weight(1f)
                            .align(CenterVertically),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.width(12.dp))
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "notification",
                        modifier = Modifier
                            .pressClick {
                                //todo
                            }
                            .align(CenterVertically),
//                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(12.dp))
                }
            },
        ) {

            Box(
                Modifier.padding(it)
            ) {
                //region map view
                //endregion

                OutlinedTextField(
                    value = value,
                    label = "userId",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}