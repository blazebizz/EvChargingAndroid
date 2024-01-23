 package ai.heart.feature.booking.screen.charging


import com.blaze.core.ui.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChargingScreen(){
    Box(Modifier.statusBarsPadding().fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(Modifier.fillMaxSize()) {

        }
        Image(painterResource(R.drawable.b1),null)
    }
}