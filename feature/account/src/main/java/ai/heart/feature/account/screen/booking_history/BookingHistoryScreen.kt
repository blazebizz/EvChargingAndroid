package ai.heart.feature.account.screen.booking_history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.components.ToolBar

@Composable
fun BookingHistoryScreen(
    navController: NavController,
    viewModel: BookingHistoryViewModel,
    coreVM: CoreViewModel
) {


}

@Composable
@Preview(showBackground = true)
fun BookingHistoryScreenImpl() {
    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(16.dp)) {
        ToolBar(title = "Your Booking History", onBackPress = {})
        
    }
}

@Composable
@Preview (showBackground = true)
fun BookingComponent(){

}
