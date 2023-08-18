package com.blaze.feature.dashboard.screen.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.utils.navigation.DashboardRoute

@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(DashboardRoute.SideNavigationScreen.route)
                        }
                        .size(35.dp)
                )
                Spacer(Modifier.width(12.dp))
                OutlinedTextField(
                    value = "Your current Location",
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            Icons.Default.Search, contentDescription = "Search"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.width(12.dp))
            }
        },

        ) {

        Column(Modifier.padding(it)) {
            Text(text = "dashboard screen")
        }
    }
}