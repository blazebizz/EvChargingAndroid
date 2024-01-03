package com.blaze.feature.dashboard.screen.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.components.pressClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    activateBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    coreVM: CoreViewModel,
    navController: NavController
) {
    SheetContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetContent() {
    val scrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(text = "Rajesh Charging Spot")
        Text(text = "4.6 ⭐ ⭐ ⭐ ⭐ ⭐ (999)")
        Row(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .horizontalScroll(horizontalScrollState)
        ) {
            for (i in 0 until 4) {
                Image(
                    painter = painterResource(id = com.blaze.core.ui.R.drawable.logo_square),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.FillHeight
                )
            }
        }


    }
}


@Composable
fun TimeSlotLayout(modifier: Modifier = Modifier) {
    Column(modifier) {

        Row {
            TimeSlotItem(1)
            TimeSlotItem(2)
            TimeSlotItem(3)
            TimeSlotItem(4)
            TimeSlotItem(5)
            TimeSlotItem(6)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            TimeSlotItem(7)
            TimeSlotItem(8)
            TimeSlotItem(9)
            TimeSlotItem(10)
            TimeSlotItem(11)
            TimeSlotItem(12)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            TimeSlotItem(13)
            TimeSlotItem(14)
            TimeSlotItem(15)
            TimeSlotItem(16)
            TimeSlotItem(17)
            TimeSlotItem(18)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            TimeSlotItem(19)
            TimeSlotItem(20)
            TimeSlotItem(21)
            TimeSlotItem(22)
            TimeSlotItem(23)
            TimeSlotItem(24)
        }

    }
}

@Composable
fun TimeSlotItem(i: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier
                .size(height = 23.dp, width = 20.dp)
                .background(Color.LightGray)
        )
        Box(
            Modifier
                .size(35.dp)
                .background(Color.LightGray, RoundedCornerShape(6.dp))
                .border(1.dp,MaterialTheme.colorScheme.onSurface, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$i")
        }
        Box(
            Modifier
                .size(height = 23.dp, width = 20.dp)
                .background(Color.LightGray)
        )
    }
}