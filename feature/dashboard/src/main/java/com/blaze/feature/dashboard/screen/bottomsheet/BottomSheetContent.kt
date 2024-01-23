package com.blaze.feature.dashboard.screen.bottomsheet

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.NextSevenDayPicker
import com.blaze.core.ui.components.TimeViewer
import com.blaze.core.ui.components.bounceClick
import com.blaze.core.utils.navigation.BookingRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    activateBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    coreVM: CoreViewModel, navController: NavController
) {
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Shree Vihar Chandrasekharpur, Bhubaneswer, Odisha 751017, India asdfadfadfsdfaesdf afrsdfaf")


        Row(Modifier.padding(top = 14.dp)) {
            Row(Modifier
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
                    text = "Navigate", color = MaterialTheme.colorScheme.primary
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
                    text = "Share", color = MaterialTheme.colorScheme.primary
                )
            }
        }

        val tagList = listOf(
            "Ola S1 pro chrager",
            "No Charger Available",
            "No Charger ",
            "No",
            "No Charger Available",
            "No Charger Available",
            "Ola S1 pro chrager",
            "No Charger Available",
            "No Charger ",
            "No",
            "No Charger Available",
            "No Charger Available"
        )
//        val tagList = emptyList<String>()

        Spacer(modifier = Modifier.height(10.dp))

        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Adaptive(35.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 75.dp),
            horizontalItemSpacing = 5.dp
        ) {
            items(tagList) {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(
                            MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp)
                        )
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        //image row

/*

        Row(
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(150.dp)
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
        }*/

        Text(
            text = "Choose a spot",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 14.dp)
        )
        val list = SnapshotStateList<SlotData>()

        list.addAll(
            listOf(
                SlotData(name = "one"),
                SlotData(name = "two"),
                SlotData(name = "three"),
                SlotData(name = "four", remark = "C"),
                SlotData(name = "four")
            )
        )

        var selectedSlot by remember { mutableStateOf<SlotData?>(null) }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 175.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(list) { slotData ->
                Box(
                    Modifier

                        .padding(2.dp)
                        .blur(
                            150.dp,
                            edgeTreatment = BlurredEdgeTreatment(shape = RoundedCornerShape(5.dp))
                        )
                        .background(
                            brush = Brush.horizontalGradient(
                                if (!slotData.selected) listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primary
                                ) else listOf(
                                    MaterialTheme.colorScheme.secondary,
                                    MaterialTheme.colorScheme.secondary,
                                )
                            ), RoundedCornerShape(5.dp)
                        )


                        .height(55.dp)

                )


                Row(
                    Modifier
                        .bounceClick {
                            // Set the selected item and update the list
                            selectedSlot = slotData.copy(selected = true)
                            list.replaceAll { it.copy(selected = it == slotData) }
                        }

                        .padding(2.dp)
                        .border(
                            1.dp,
                            if (!slotData.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer,
                            RoundedCornerShape(5.dp)
                        )
                        .height(55.dp)
//                        .blur(16.dp)
                    , verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = if (slotData.remark == "B") R.drawable.bike_img else R.drawable.car_img),
                        contentDescription = "",
//                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxHeight()
                            .clip(RectangleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Spot ${slotData.name}")
                }
            }
        }


        val context = LocalContext.current

        Text(
            text = "When",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 14.dp)
        )

        Column (horizontalAlignment = Alignment.CenterHorizontally){
            TimeViewer(onTimeSelected = {hour, minute, meridian ->

            })
            Spacer(Modifier.height(10.dp))
            NextSevenDayPicker(){

            }

        }





        Text(text = "Charging price breakup.",
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .bounceClick {

                }
                .padding(top = 10.dp)
                .fillMaxWidth())

        Button(
            text = "Book", modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            navController.navigate(BookingRoute.ChargingScreen.route)
        }
    }
}

data class SlotData(
    val name: String = "", val remark: String = "B", var selected: Boolean = false
)

