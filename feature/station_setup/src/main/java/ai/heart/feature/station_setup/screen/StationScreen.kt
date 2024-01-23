package ai.heart.feature.station_setup.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.TimePickDialog
import com.blaze.core.ui.components.TimeViewer
import com.blaze.core.ui.components.bounceClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun StationScreen() {

    val list = SnapshotStateList<SlotData>()
    val context = LocalContext.current
    list.addAll(
        listOf(
            SlotData(name = "one"),
            SlotData(name = "two"),
            SlotData(name = "three"),
            SlotData(name = "four", remark = "C"),
            SlotData(name = "five")
        )
    )

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


    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.clickable {
//                navController.popBackStack()
            })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Station Management", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Rajesh Charging Spot", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "4.6 ⭐ ⭐ ⭐ ⭐ ⭐ (999)")
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "OPEN", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "status", fontSize = 10.sp)
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Create, contentDescription = null, Modifier.size(30.dp))
            Text(text = "Shree Vihar Chandrasekharpur, Bhubaneswer, Odisha 751017, India asdfadfadfsdfaesdf afrsddsvzdvsdfaf")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Opening/Closing Time",
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            //
     /*       Row(
                Modifier
                    .bounceClick {
                        state.value = openingTimeState
                        showTimePicker.value = true
                    }
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .height(50.dp)) {
                Row(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                    Text(
                        text = "${openingTimeState.hour}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = " : ", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${openingTimeState.minute}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column {
                    Text(
                        text = "AM",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 10.dp, top = 3.dp, end = 10.dp
                        )
                    )
                    Text(
                        text = "PM",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 10.dp, bottom = 3.dp, end = 10.dp
                        )
                    )
                }
            }*/

            TimeViewer(){hour, minute, meridian ->
                Toast.makeText(context, "$hour : $minute $meridian", Toast.LENGTH_SHORT).show()
            }


            Spacer(modifier = Modifier.weight(1f))
            Text(text = "to")
            Spacer(modifier = Modifier.weight(1f))

            TimeViewer(){hour, minute, meridian ->
                Toast.makeText(context, "$hour : $minute $meridian", Toast.LENGTH_SHORT).show()
            }
        }



        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "Charger you have", fontWeight = FontWeight.Bold)
            Text(
                text = "Add", modifier = Modifier
                    .border(
                        1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            )

        }
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

        Text(
            text = "Spots", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 14.dp)
        )


        var selectedSlot by remember { mutableStateOf<SlotData?>(null) }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .weight(1f),
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


            item {
                Box(
                    Modifier

                        .padding(2.dp)
                        .blur(
                            150.dp,
                            edgeTreatment = BlurredEdgeTreatment(shape = RoundedCornerShape(5.dp))
                        )
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primary
                                )
                            ), RoundedCornerShape(5.dp)
                        )
                        .height(55.dp)
                )


                Row(
                    Modifier
                        .bounceClick {
                            // Set the selected item and update the list

                        }
                        .padding(2.dp)
                        .border(
                            1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp)
                        )
                        .height(55.dp)
//                        .blur(16.dp)
                    , verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        Icons.Default.Add, contentDescription = "",
//                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxHeight()
                            .clip(RectangleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Add New Slot")
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(text = "Save", modifier = Modifier.fillMaxWidth()) {

        }
    }
}

data class SlotData(
    val name: String = "", val remark: String = "B", var selected: Boolean = false
)

//#00rtsp00