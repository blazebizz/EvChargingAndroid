package ai.heart.feature.booking.screen.booking_status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview(showBackground = true)
fun BookingStatusScreen() {
    Box(modifier =Modifier.fillMaxSize()
//        .background(Color.Black)
        .statusBarsPadding()){
        Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
//your slot has been booked successfully, we will keep updating


        }
    }
}

