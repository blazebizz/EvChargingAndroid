package com.blaze.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blaze.core.ui.R

@Composable
fun OutLinedTextFieldSpinner(
    spinnerState: MutableState<Boolean>,
    text: MutableState<String>,
    list: List<String>,
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    readOnly: Boolean = false,
    isError: Boolean = false,
) {

//    val bList = remember { SnapshotStateList<String>() }
//    bList.addAll(list)


    Box(
        modifier = modifier
//        .padding(0.dp)
            .fillMaxWidth()
//        .bounceClick {
//            spinnerState.value = true
//        }
        ,
//        contentAlignment = Alignment.CenterEnd
    ) {
        androidx.compose.material3.OutlinedTextField(modifier = modifier,
            value = text.value,
            onValueChange = { t ->
                text.value = t
//                spinnerState.value = true
//                bList.clear()
//                list.forEach {
//                    if (it.contains(t)) {
//                        bList.add(it)
//                    }
//                }
            },
            label = {
                Text(text = label)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType, imeAction = imeAction
            ),
            singleLine = true,
            readOnly = readOnly,
            isError = isError,
            trailingIcon = {

                Icon(painter = painterResource(id = if (!spinnerState.value) R.drawable.baseline_keyboard_arrow_down_24 else R.drawable.baseline_keyboard_arrow_up_24),
                    contentDescription = null,
                    modifier = Modifier
                        .pressClick {
                            spinnerState.value = !spinnerState.value
                        })
            })

        val width = LocalConfiguration.current.screenWidthDp - 60

        Spacer(modifier = Modifier.height(10.dp))
        DropdownMenu(
            expanded = spinnerState.value,
            onDismissRequest = { spinnerState.value = false },
            modifier = Modifier
//                .width(with(LocalDensity.current) {
//                    textFieldSize.value.width.toDp()
//                })
                .padding(8.dp)
                .heightIn(max = 200.dp)
                .width(width.dp)
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                    onClick = {
                        text.value = it
                        spinnerState.value = false
                    },
//                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}