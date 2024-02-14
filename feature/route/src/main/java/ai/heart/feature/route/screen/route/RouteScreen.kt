package ai.heart.feature.route.screen.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.blaze.core.ui.components.bounceClick

@Composable
fun RouteScreen(viewModel: RouteViewModel) {
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Route Screen", modifier =Modifier.bounceClick {
            viewModel.getDirection()
        })
        Spacer(modifier = Modifier.weight(1f))

    }
}