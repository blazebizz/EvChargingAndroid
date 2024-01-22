package com.blaze.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.blaze.core.ui.DefaultShape
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
@Preview
fun PreviewTimePickDialog() {
    TimeViewer { hour, minute, meridian ->

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeViewer(
    onTimeSelected: (hour: Int, minute: Int, meridian: String) -> Unit,
) {
    val state = rememberTimePickerState()
    val showTimePicker = remember { mutableStateOf(false) }
    val meridian = remember { mutableStateOf("AM") }
    val hour = remember { mutableIntStateOf(0) }
    val min = remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = state.hour, key2 = state.is24hour) {
        if (!state.is24hour) {
            if (state.hour > 12) {
                hour.intValue = state.hour - 12
                meridian.value = "PM"
            } else {
                hour.intValue = state.hour
                when (state.hour) {
                    0 -> {
                        hour.intValue = 12
                        meridian.value = "AM"
                    }

                    12 -> {
                        meridian.value = "PM"
                    }

                    else -> {
                        meridian.value = "AM"
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = state.minute) {
        min.intValue = state.minute
    }

    Row(
        Modifier
            .bounceClick {
                showTimePicker.value = true
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(10.dp)
            )
            .height(50.dp)) {
        Row(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
            Text(
                text = if (hour.intValue.toString().length == 1) "0${hour.intValue}" else "${hour.intValue}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = " : ", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(
                text = if (min.intValue.toString().length == 1) "0${min.intValue}" else "${min.intValue}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = "AM",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(
                        if (meridian.value == "AM") MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(bottomStart = 5.dp, topEnd = 10.dp)
                    )
                    .padding(
                        start = 10.dp, top = 3.dp, end = 10.dp
                    )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "PM",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(
                        if (meridian.value == "PM") MaterialTheme.colorScheme.primary else Color.Transparent,
                        RoundedCornerShape(bottomEnd = 10.dp, topStart = 5.dp)
                    )

                    .padding(
                        start = 10.dp, bottom = 3.dp, end = 10.dp
                    )
            )
        }
    }

    TimePickDialog(showTimePicker = showTimePicker, state = state, onTimeSelected)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickDialog(
    showTimePicker: MutableState<Boolean>,
    state: TimePickerState,
    onTimeSelected: (hour: Int, minute: Int, meridian: String) -> Unit,
) {
    if (showTimePicker.value) {
        Dialog(onDismissRequest = { }) {
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.background, DefaultShape)
                    .padding(16.dp)
            ) {
                TimePicker(
                    state = state, layoutType = TimePickerLayoutType.Vertical, modifier = Modifier
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
//                    Button(text = "Cancel") {
//                        onNotSelected()
//                        showTimePicker.value = false
//                    }

                    Button(text = "Done") {
                        var hr = 0
                        val min = state.minute
                        var mr = "AM"
                        if (!state.is24hour) {
                            if (state.hour > 12) {
                                hr = state.hour - 12
                                mr = "PM"
                            } else {
                                hr = state.hour
                                when (state.hour) {
                                    0 -> {
                                        hr = 12
                                        mr = "AM"
                                    }

                                    12 -> {
                                        mr = "PM"
                                    }

                                    else -> {
                                        mr = "AM"
                                    }
                                }
                            }
                        }

                        onTimeSelected(hr, min, mr)
                        showTimePicker.value = false
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun NextSevenDayPicker(modifier:Modifier = Modifier,onSelected: (date: DateInfo) -> Unit = {}) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    Row(modifier) {
        LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            itemsIndexed(getSevenDays()) { index, data ->
                Column(
                    modifier = Modifier
                        .bounceClick {
                            selectedIndex.intValue = index
                            onSelected(data)
                        }
                        .padding(horizontal = 3.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(5.dp)
                        )
                        .background(
                            if (selectedIndex.intValue == index) MaterialTheme.colorScheme.primary else Color.Transparent,
                            RoundedCornerShape(5.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${data.day}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text(text = data.monthText.substring(0, 3).uppercase(), fontSize = 10.sp)
                    Text(text = "${data.year}", fontSize = 10.sp)
                }
            }
        }
    }

}


data class DateInfo(
    val day: Int,
    val month: Int,
    val year: Int,
    val monthText: String,
)

fun getSevenDays(): List<DateInfo> {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)

    val dateList = mutableListOf<DateInfo>()

    for (i in 0 until 7) {
        val nextDate = currentDate.plusDays(i.toLong())
        val dateInfo = DateInfo(
            day = nextDate.dayOfMonth,
            month = nextDate.monthValue,
            year = nextDate.year,
            monthText = nextDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        )
        dateList.add(dateInfo)
    }

    return dateList
}



