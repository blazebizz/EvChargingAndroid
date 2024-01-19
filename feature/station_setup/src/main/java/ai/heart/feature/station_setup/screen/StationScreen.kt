package ai.heart.feature.station_setup.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blaze.core.ui.R
import com.blaze.core.ui.components.bounceClick

@Composable
@Preview(showBackground = true)
fun StationScreen(){
    Column (Modifier.statusBarsPadding().fillMaxSize().padding(16.dp)){
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.clickable {
//                navController.popBackStack()
            })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Station Management", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
        Text(text = "Rajesh Charging Spot")
        Text(text = "4.6 ⭐ ⭐ ⭐ ⭐ ⭐ (999)")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Shree Vihar Chandrasekharpur, Bhubaneswer, Odisha 751017, India asdfadfadfsdfaesdf afrsdfaf")


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
                            1.dp,
                           MaterialTheme.colorScheme.primary ,
                            RoundedCornerShape(5.dp)
                        )
                        .height(55.dp)
//                        .blur(16.dp)
                    , verticalAlignment = Alignment.CenterVertically) {
                    Image(
                       Icons.Default.Add,
                        contentDescription = "",
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

    }
}
data class SlotData(
    val name: String = "", val remark: String = "B", var selected: Boolean = false
)