package ai.heart.feature.booking.screen.booking_status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.bounceClick


@Composable
@Preview(showBackground = true)
fun BookingStatusScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//your slot has been booked successfully, we will keep updating

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(

                        )
                    ) {
                        append("Your Ticket Has Been")
                    }

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 42.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("\n\nConfirmed")
                    }

                }, textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "We keep you updated about booking status !")
//booking id
            //vechile type, vechile model,
            //operator details
            //slot code
            //operator code,
            //booking start and end info
            //cancel option
            //resedule option
            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
//                elevation = CardElevation(3.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(Modifier.padding(16.dp)) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp),
                        horizontalArrangement = Arrangement.End
                    ) {

//                        Text(text = "Charging Ticket Details")
                        Spacer(modifier = Modifier.weight(1f))

                        Row(Modifier.bounceClick { }) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.baseline_file_download_24
                                ), contentDescription = null
                            )
                            Text(
                                text = "Cancel",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }



                    item()
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_file_download_24
                            ), contentDescription = null
                        )
                        Text(
                            text = "Receipt",
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))


            Button(
                text = "OK", Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview
@Composable
fun item() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp)
    ) {
        Text(text = "name", Modifier.weight(1f))
        Text(text = "value", Modifier.weight(1f))
    }
}