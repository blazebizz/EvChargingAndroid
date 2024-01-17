package com.blaze.feature.dashboard.screen.bottomsheet

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Preview(showBackground = true)
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Shree Vihar Chandrasekharpur, Bhubaneswer, Odisha 751017, India asdfadfadfsdfaesdf afrsdfaf")


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
        }

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
        var hourText by remember { mutableStateOf("0") }
        var minText by remember { mutableStateOf("0") }
        var amPmText by remember { mutableStateOf("AM") }

        Row(Modifier.padding(top = 10.dp), verticalAlignment = Alignment.Bottom) {
            Text(text = "Check In Time")

            BasicTextField(
                enabled = false,

                value = hourText,
                onValueChange = {
                    hourText = it
                },
                modifier = Modifier
                    .width(50.dp)
                    .scrollable(
                        orientation = Orientation.Vertical,
                        state = rememberScrollableState { delta ->
                            if (delta.toInt() % 5 == 0) {
                                hourText = if (hourText.toInt() in 1..12) {
                                    (hourText.toInt() - (delta / 5))
                                        .toInt()
                                        .toString()
                                } else {
                                    "0"
                                }
                            }
                            delta
                        },
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End
                )
            )
            Text(
                text = " : ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )

            BasicTextField(
                enabled = false,

                value = minText,
                onValueChange = {
                    minText = it
                },
                modifier = Modifier
                    .width(50.dp)
                    .scrollable(
                        orientation = Orientation.Vertical,
                        state = rememberScrollableState { delta ->
                            if (delta.toInt() % 5 == 0) {
                                minText = if (minText.toInt() in 1..12) {
                                    (minText.toInt() - (delta / 5))
                                        .toInt()
                                        .toString()
                                } else {
                                    "0"
                                }
                            }
                            delta
                        },
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start
                )
            )



            BasicTextField(
                enabled = false,
                value = amPmText,
                onValueChange = {},
                modifier = Modifier
                    .width(50.dp)
                    .scrollable(
                        orientation = Orientation.Vertical,
                        state = rememberScrollableState { delta ->
                            if (delta.toInt() % 5 == 0) {
                                if (delta < 0) {
                                    amPmText = "AM"
                                } else {
                                    amPmText = "PM"
                                }
                            }
                            delta
                        },
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start
                )
            )

        }




        Text(text = "Charging price breakup.",
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .bounceClick {

                }
                .padding(top = 10.dp))

        Button(
            text = "Book", modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Toast.makeText(context, "selected slot ${selectedSlot?.name}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

data class SlotData(
    val name: String = "", val remark: String = "B", var selected: Boolean = false
)


//@Composable
//fun SetDuration(
//    hour: MutableState<String>,
//    pMin: MutableState<String>,
//    sMin: MutableState<String>
//) {
//    Column(modifier = Modifier) {
//        Row(Modifier.fillMaxWidth()) {
//            Spacer(modifier = Modifier.weight(2f))
//            OutlinedTextField(
//                value = pMin.value,
//                modifier = Modifier
//                    .weight(3f)
//                    .background(color = MaterialTheme.colors.background)
//                    .scrollable(orientation = Orientation.Vertical,
//                        state = rememberScrollableState { delta ->
//                            if (delta.toInt() % 5 == 0) {
//                                if (pMin.value.toInt() >= 0) {
//                                    pMin.value = (pMin.value.toInt() - (delta / 5))
//                                        .toInt()
//                                        .toString()
//                                } else {
//                                    pMin.value = "0"
//                                }
//                            }
//
//                            delta
//                        }),
//                textStyle = TextStyle(
//                    color = MaterialTheme.colors.secondary,
//                    fontSize = 70.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                ),
//                onValueChange = {
//                    pMin.value = it
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                maxLines = 1,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = MaterialTheme.colors.secondary,
//                    unfocusedBorderColor = MaterialTheme.colors.background
//                ),
//                singleLine = true,
//            )
////            TextField(value = pMin.value, onValueChange = {
////                pMin.value = it
////            },
////                textStyle = TextStyle()
////
////            )
//            Text(text = " / ", Modifier.align(CenterVertically), fontSize = 40.sp)
//            OutlinedTextField(
//                value = sMin.value,
//                modifier = Modifier
//                    .weight(3f)
//                    .background(color = MaterialTheme.colors.background)
//                    .scrollable(orientation = Orientation.Vertical,
//                        state = rememberScrollableState { delta ->
//                            if (delta.toInt() % 5 == 0) {
//                                if (sMin.value.toInt() >= 0) {
//                                    sMin.value = (sMin.value.toInt() - (delta / 5))
//                                        .toInt()
//                                        .toString()
//                                } else {
//                                    sMin.value = "0"
//                                }
//                            }
//
//                            delta
//                        }),
//                textStyle = TextStyle(
//                    color = MaterialTheme.colors.primary,
//                    fontSize = 70.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                ),
//                onValueChange = {
//                    sMin.value = it
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                maxLines = 1,
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = MaterialTheme.colors.primary,
//                    unfocusedBorderColor = MaterialTheme.colors.background
//                ),
//                singleLine = true,
//            )
//            Spacer(modifier = Modifier.weight(2f))
//
//        }
//
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .padding(0.dp, 0.dp, 0.dp, 8.dp)
//        ) {
//            Spacer(modifier = Modifier.weight(1f))
//            Text(text = " min ", Modifier.align(CenterVertically), fontSize = 10.sp)
//            Spacer(modifier = Modifier.weight(1f))
//            Text(text = " min ", Modifier.align(CenterVertically), fontSize = 10.sp)
//            Spacer(modifier = Modifier.weight(1f))
//        }
//
//
//        OutlinedTextField(
//            value = hour.value,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .background(color = MaterialTheme.colors.background)
//                .scrollable(orientation = Orientation.Vertical,
//                    state = rememberScrollableState { delta ->
//                        if (delta.toInt() % 5 == 0) {
//                            if (hour.value.toInt() >= 0) {
//                                hour.value = (hour.value.toInt() - (delta / 5))
//                                    .toInt()
//                                    .toString()
//                            } else {
//                                hour.value = "0"
//                            }
//                        }
//
//                        delta
//                    }),
//            textStyle = TextStyle(
//                color = Color.Gray,
//                fontSize = 70.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center
//            ),
//            onValueChange = {
//                hour.value = it
//            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            maxLines = 1,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Gray,
//                unfocusedBorderColor = MaterialTheme.colorScheme.background
//            ),
//            singleLine = true,
//        )
//        Text(text = " hour ", Modifier.align(Alignment.CenterHorizontally), fontSize = 10.sp)
//    }
//}
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