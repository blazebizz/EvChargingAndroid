package com.blaze.feature.dashboard.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.DefaultShape
import com.blaze.core.ui.R
import com.blaze.core.ui.components.TopBarEditable
import com.blaze.core.ui.components.pressClick
import com.blaze.core.utils.util.logi
import com.blaze.core.utils.util.mainScope
import com.blaze.core.utils.util.tst
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel, coreVM: CoreViewModel) {

    Column(
        Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .statusBarsPadding()
        ) {
            TopBarEditable(text = coreVM.searchText, onValueChange = {

                    // Set the locationText to the entered text
                    coreVM.searchText.value = it
                    viewModel.searchPlaces(it)

            }, trailingIcon = R.drawable.baseline_close_24, trailingOnClick = {
                coreVM.searchText.value = ""
            }, headerIcon = R.drawable.arrow_small_left_24, headerOnClick = {
                navController.popBackStack()
            })
        }
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            if (viewModel.locationAutofill.isEmpty()) {
                RecentSearchBox()
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(items = viewModel.locationAutofill) {
                        Row(
                            Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 5.dp)
                                .defaultMinSize(minHeight = 50.dp)
                                .background(MaterialTheme.colorScheme.background, DefaultShape)
                                .pressClick {
                                    coreVM.searchText.value = it.address
                                    viewModel.getCoordinates(it) {
                                        logi("SearchScreen: lat: ${it.latitude}  lng: ${it.longitude}")
                                        coreVM.setMapLocation(it)
                                        navController.popBackStack()
                                    }
                                }
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = null,
                                Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "${it.address}",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
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