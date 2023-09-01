package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blaze.core.ui.R
import com.blaze.core.ui.components.bounceClick
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun VehicleSelectionScreen(subNavController: NavHostController, viewModel: OnBoardingViewModel) {
    val selected2Wheeler = rememberSaveable { mutableStateOf(false) }
    val selected4Wheeler = rememberSaveable { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(
                Modifier
                    .bounceClick { selected2Wheeler.value = !selected2Wheeler.value }
                    .padding(5.dp)

                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.charging_2_wheeler),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp,MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(8.dp)
                    )
                )
                RadioButton(selected = selected2Wheeler.value, onClick = { /*TODO*/ } ,
                    modifier = Modifier
                        .padding(5.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background, CircleShape
                    ),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.onBackground
                    ))
            }

            Box(
                Modifier
                    .bounceClick { selected4Wheeler.value = !selected4Wheeler.value }
                    .padding(5.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.charging_4_wheeler),
                    contentDescription = null,modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp,MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(8.dp)
                        )
                )
                RadioButton(
                    selected = selected4Wheeler.value,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background, CircleShape
                        ),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Requirements and Instructions", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "This part is for instructions and requirements.")
    }
}