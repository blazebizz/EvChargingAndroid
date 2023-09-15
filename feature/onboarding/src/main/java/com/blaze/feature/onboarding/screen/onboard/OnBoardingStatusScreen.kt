package com.blaze.feature.onboarding.screen.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.ui.R
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.OutlinedTextField
import com.blaze.core.ui.components.pressClick
import com.blaze.core.utils.navigation.OnBoardingRoute
import com.blaze.core.utils.util.USER_ID
import com.blaze.feature.onboarding.screen.OnBoardingViewModel

@Composable
fun OnBoardingStatusScreen(
    navController: NavController, viewModel: OnBoardingViewModel, coreUi: CoreUiViewModel
) {
    var boardedUserStatus by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchOnBoardUserData(USER_ID, coreUi.loading)
    }

    LaunchedEffect(key1 = viewModel.fetchedOnBoardUserData.value?.data) {
        if (viewModel.fetchedOnBoardUserData.value?.data?.isEmpty() == true) {
            boardedUserStatus = 0
            return@LaunchedEffect
        }

        if (viewModel.fetchedOnBoardUserData.value?.data?.isNotEmpty() == true) {
            viewModel.fetchedOnBoardUserData.value?.data?.forEach {
                if (it?.status.equals("APPROVE")) {
                    boardedUserStatus = 1
                    return@LaunchedEffect
                }
            }
        }

        if (viewModel.fetchedOnBoardUserData.value?.data?.isNotEmpty() == true) {
            viewModel.fetchedOnBoardUserData.value?.data?.forEach {
                if (it?.status.equals("PENDING")) {
                    boardedUserStatus = 2
                    return@LaunchedEffect
                }
            }
        }
        if (viewModel.fetchedOnBoardUserData.value?.data?.isNotEmpty() == true) {
            viewModel.fetchedOnBoardUserData.value?.data?.forEach {
                if (it?.status.equals("REJECT")) {
                    boardedUserStatus = 3
                    return@LaunchedEffect
                }
            }
        }
    }

    when (boardedUserStatus) {
        0 -> {
            NewOnBoardingUser {
                navController.navigate(OnBoardingRoute.OnBoardingScreen.route) {
                    popUpTo(OnBoardingRoute.OnBoardingScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        1 -> {
            OnBoardedUser(viewModel)
        }

        2 -> {
            PendingStatusScreen(onBack = {
                navController.popBackStack()
            }, onModify = {
                navController.navigate(OnBoardingRoute.OnBoardingScreen.route)
            })
        }

        3 -> {
            RejectStatusScreen()
        }

        else -> {
            //todo
        }
    }

}

@Composable
fun NewOnBoardingUser(onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo_square),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Welcome to the Future of Mobility! 🚗⚡",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )



        Spacer(Modifier.height(10.dp))
        Text(
            text = "Join us in creating a greener world by providing electric vehicle charging solutions. Let's power up the future together!",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground

        )
        Spacer(modifier = Modifier.weight(1f))
        Button(text = "Register Now", onClick = onClick)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun OnBoardedUser(viewModel: OnBoardingViewModel) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .statusBarsPadding()
            .verticalScroll(scrollState)
            .padding(16.dp)) {

        Text(
            text = "Basic Details",
            Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "${viewModel.fetchedOnBoardUserData.value?.data?.get(0)?.onboardData?.basicDetails?.name}",
            label = "Full Name",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = "${viewModel.fetchedOnBoardUserData.value?.data?.get(0)?.onboardData?.basicDetails?.mobile}",
            label = "Mobile Number"
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = "${viewModel.fetchedOnBoardUserData.value?.data?.get(0)?.onboardData?.basicDetails?.address1}",
            label = "Address Lane 1"
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = "${viewModel.fetchedOnBoardUserData.value?.data?.get(0)?.onboardData?.basicDetails?.address2}",
            label = "Address Lane 2"
        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun PendingStatusScreen(
    onBack: () -> Unit = {},
    onModify: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo_square),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Verification in Progress",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground

        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "We've received it and will now begin the process of verifying your details. Please allow us some time to onboard you.",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground

        )
        Spacer(modifier = Modifier.weight(1f))
        Button(text = "Return to Dashboard", onClick = onBack)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Modify Your Application",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.pressClick {
                onModify()
            })
    }
}

@Composable
fun RejectStatusScreen( onBack: () -> Unit = {},
                        onModify: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo_square),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Rejected",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground

        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "We appreciate your interest, but we regret to inform you that your application has been declined at this time. Don't be discouraged! We believe in second chances. You are welcome to reapply in the future if your circumstances change or if you believe you now meet our requirements.",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground

        )

        Spacer(Modifier.height(10.dp))
        Text(
            text = "",
            Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground

        )
        Spacer(modifier = Modifier.weight(1f))
        Button(text = "Return to Dashboard", onClick = onBack)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Modify Your Application",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.pressClick {
                onModify()
            })
    }
}