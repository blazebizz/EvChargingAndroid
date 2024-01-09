package com.blaze.feature.dashboard.screen.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.bounceClick

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

@Preview
@Composable
fun SheetContent() {
    val scrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxWidth()
//            .fillMaxHeight(0.6f)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(text = "Operated by Rajesh Charging Spot")
        Text(text = "4.6 ⭐ ⭐ ⭐ ⭐ ⭐ (999)")


        Row(Modifier.padding(top = 14.dp)) {
            Row(
                Modifier
                    .bounceClick {

                    }
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
                    .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(id = R.drawable.location),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Navigate",
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                Modifier
                    .bounceClick {

                    }
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
                    .padding(5.dp)) {
                Icon(
                    painterResource(id = R.drawable.share),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Share",
                    color = MaterialTheme.colorScheme.primary
                )
            }


        }

        Row(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(200.dp)
                .horizontalScroll(horizontalScrollState)
        ) {
            for (i in 0 until 4) {
                Image(
                    painter = painterResource(id = R.drawable.logo_square),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.FillHeight
                )
            }
        }

        Text(text = "Select Slot", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top=14.dp))
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(250.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(list) {

                Row(
                    Modifier
                        .bounceClick {

                        }
                        .padding(5.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
                        .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(id = R.drawable.location),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Navigate",
                    )
                }
            }
        }

        Button(text = "Book",modifier = Modifier.padding(top = 16.dp).fillMaxWidth()) {
            
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
                .border(1.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(6.dp)),
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