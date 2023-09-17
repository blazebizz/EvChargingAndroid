package com.blaze.feature.dashboard.screen.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    activateBottomSheet: MutableState<Boolean>,
    sheetState: BottomSheetScaffoldState,
    coreVM: CoreViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .verticalScroll(scrollState)
    ) {

    }
}