package com.blaze.feature.dashboard.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.TopBarEditable
import com.blaze.core.utils.util.USER_ID

@Composable
fun SearchScreen(navController: NavController, coreVM: CoreViewModel) {
    LaunchedEffect(key1 = coreVM.searchText.value) {
        USER_ID = coreVM.searchText.value
    }

    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant).statusBarsPadding()) {
            TopBarEditable(
                text = coreVM.searchText,
                trailingIcon = R.drawable.baseline_close_24,
                trailingOnClick = {
                    coreVM.searchText.value = ""
                },
                headerIcon = R.drawable.arrow_small_left_24,
                headerOnClick = {
                    navController.popBackStack()
                })
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            RecentSearchBox()
        }
    }
}

@Composable
fun RecentSearchBox() {
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(text = "Recent")
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Outlined.Info, contentDescription = null)
        }
        Box(
            Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(90.dp)
        ) {

        }
        Box(
            Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(90.dp)
        ) {}
        Box(
            Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(90.dp)
        ) {}
    }

}