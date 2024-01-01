package ai.heart.feature.account.screen.safety

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreViewModel
import com.blaze.core.ui.defaultBackground
import com.blaze.core.utils.navigation.AccountRoute


@Composable
fun SafetyScreen(
    navController: NavController, coreVM: CoreViewModel, viewModel: SafetyScreenViewModel
) {
    Column(
        Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.clickable {
                navController.popBackStack()
            })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Safety toolkit", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
        }

        val context = LocalContext.current
        Text(text = "With us, your safety comes first. Here are some measures and provisions to ensure your safety.")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "know more",
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.clickable {
                Toast.makeText(context, "to redirect webpage", Toast.LENGTH_SHORT).show()
            })
        Spacer(modifier = Modifier.height(10.dp))

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Settings", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            Modifier
                .clickable {
                    navController.navigate(AccountRoute.AddContactScreen.route)
                }
                .fillMaxWidth()
                .defaultBackground()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(text = "New trusted contacts", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "share charging details with your loved ones in a single tap")
            }
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
        }

    }
}
