package com.blaze.feature.onboarding.screen.onboard

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaze.core.ui.CoreUiViewModel
import com.blaze.core.ui.components.Button
import com.blaze.core.ui.components.OutlinedButton
import com.blaze.core.ui.ui.theme.SeaSalt
import com.blaze.data.onboarding.model.req.BankDetails
import com.blaze.data.onboarding.model.req.BasicDetails
import com.blaze.data.onboarding.model.req.IdentityDetails
import com.blaze.data.onboarding.model.req.OnboardData
import com.blaze.data.onboarding.model.req.PartnerOnBoardRequest
import com.blaze.feature.onboarding.navigation.OnBoardingSubNavGraph
import com.blaze.feature.onboarding.navigation.OnBoardingSubScreen
import com.blaze.feature.onboarding.screen.OnBoardingViewModel


@Composable
fun OnBoardingScreen(
    navController: NavController, viewModel: OnBoardingViewModel, coreUi: CoreUiViewModel
) {

    val context = LocalContext.current


    val ap1 by animateFloatAsState(
        targetValue = viewModel.progress1.floatValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    val ap2 by animateFloatAsState(
        targetValue = viewModel.progress2.floatValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    val ap3 by animateFloatAsState(
        targetValue = viewModel.progress3.floatValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    val ap4 by animateFloatAsState(
        targetValue = viewModel.progress4.floatValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )
    val ap5 by animateFloatAsState(
        targetValue = viewModel.progress5.floatValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = ""
    )


    val onBoardingNavController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchOnBoardUserData("000002",coreUi.loading)
    }

    BackHandler {
        previousFunction(onBoardingNavController, viewModel, navController)
    }


    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .padding(start = 16.dp, top = 10.dp, bottom = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "back", Modifier.clickable {
                previousFunction(onBoardingNavController, viewModel, navController)
            })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Partner On Boarding", fontWeight = FontWeight.SemiBold)
        }


        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            RoundedTextIndicator("1")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = ap1,
                color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("2")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = ap2,
                color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("3")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = ap3,
                color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("4")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = ap4,
                color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("5")
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(4f)
                    .semantics(mergeDescendants = true) {},
                progress = ap5,
                color = MaterialTheme.colorScheme.onBackground
            )
            RoundedTextIndicator("6")
            Spacer(modifier = Modifier.weight(1f))
        }


        Column(
            Modifier
                .padding(16.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(SeaSalt)
        ) {
            OnBoardingSubNavGraph(onBoardingNavController, viewModel)
        }

        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton("Previous") {
                previousFunction(onBoardingNavController, viewModel, navController)
            }

            Spacer(modifier = Modifier.weight(3f))

            Button(text = "Continue") {
                nextFunction(onBoardingNavController, viewModel, context, onFailure = {
                    coreUi.snackbar(it)
                })
            }

            Spacer(modifier = Modifier.weight(1f))

        }
        Spacer(Modifier.height(12.dp))
    }
}

fun nextFunction(
    navController: NavHostController,
    viewModel: OnBoardingViewModel,
    context: Context,
    onFailure: (String) -> Unit = {}
) {
    when (navController.currentDestination?.route) {
        OnBoardingSubScreen.Page1.name -> {

            if (checkPage1(viewModel, onFailure)) {
                //nav to next page
                viewModel.progress1.floatValue = 1f
                navController.navigate(OnBoardingSubScreen.Page2.name) {
                    popUpTo(OnBoardingSubScreen.Page1.name) { inclusive = true }
                }
            }
        }

        OnBoardingSubScreen.Page2.name -> {
            if (checkPage2(viewModel, onFailure)) {
                if (viewModel.fetchedOnBoardUserData.value != null && viewModel.fetchedOnBoardUserData.value?.data?.isNotEmpty() == true) {
                    if (viewModel.fetchedOnBoardUserData.value?.data?.get(
                            0
                        )?.onboardData?.basicDetails?.name?.equals(
                            viewModel.fullName.value
                        ) == true
                    ) {
                        toPage3(viewModel, navController)
                    } else {
                        step12(viewModel, onFailure) { toPage3(viewModel, navController) }
                    }
                } else {
                    step12(viewModel, onFailure) { toPage3(viewModel, navController) }
                }
            }
        }

        OnBoardingSubScreen.Page3.name -> {
            if (checkPage3(viewModel, onFailure)) {
                if (viewModel.fetchedOnBoardUserData.value != null && viewModel.fetchedOnBoardUserData.value?.data?.isNotEmpty() == true) {
                    if (viewModel.fetchedOnBoardUserData.value != null && viewModel.fetchedOnBoardUserData.value?.data?.get(
                            0
                        )?.onboardData?.identityDetails?.aadhaarNo?.equals(
                            viewModel.aadharNumber.value
                        ) == true
                    ) {
                        toPage4(viewModel, navController)
                    } else {
                        step3(viewModel, onFailure) { toPage4(viewModel, navController) }
                    }
                } else {
                    step3(viewModel, onFailure) { toPage4(viewModel, navController) }
                }
            }
        }

        OnBoardingSubScreen.Page4.name -> {
            //upload doc
            if (checkPage4(viewModel, onFailure)) {
                step4(context, viewModel, onFailure) {
                    toPage5(viewModel, navController)
                }
            }
        }

        OnBoardingSubScreen.Page5.name -> {
            if (checkPage5(viewModel, onFailure)) {
                if (viewModel.fetchedOnBoardUserData.value != null && viewModel.fetchedOnBoardUserData.value?.data?.isNotEmpty() == true) {
                    if (viewModel.fetchedOnBoardUserData.value != null && viewModel.fetchedOnBoardUserData.value?.data?.get(
                            0
                        )?.onboardData?.bankDetails?.accHolderName?.equals(
                            viewModel.accNameHolder.value
                        ) == true
                    ) {
                        //nav to next page
                        toPage6(viewModel, navController)
                    } else {
                        step5(viewModel, onFailure) { toPage6(viewModel, navController) }
                    }
                } else {
                    step5(viewModel, onFailure) {
                        toPage6(viewModel, navController)
                    }
                }
            }
        }

        OnBoardingSubScreen.Page6.name -> {
            if (checkPage6(viewModel, onFailure)) {
                step6(context, viewModel, onFailure) {
                    Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            }
        }
    }
}

fun previousFunction(
    subNavController: NavHostController,
    viewModel: OnBoardingViewModel,
    mainNavController: NavController
) {
    when (subNavController.currentDestination?.route) {
        OnBoardingSubScreen.Page1.name -> {
            mainNavController.popBackStack()
        }

        OnBoardingSubScreen.Page2.name -> {
            viewModel.progress1.floatValue = 0f
            subNavController.navigate(OnBoardingSubScreen.Page1.name) {
                popUpTo(OnBoardingSubScreen.Page2.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page3.name -> {
            viewModel.progress2.floatValue = 0f
            subNavController.navigate(OnBoardingSubScreen.Page2.name) {
                popUpTo(OnBoardingSubScreen.Page3.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page4.name -> {
            viewModel.progress3.floatValue = 0f
            subNavController.navigate(OnBoardingSubScreen.Page3.name) {
                popUpTo(OnBoardingSubScreen.Page4.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page5.name -> {
            viewModel.progress4.floatValue = 0f
            subNavController.navigate(OnBoardingSubScreen.Page4.name) {
                popUpTo(OnBoardingSubScreen.Page5.name) { inclusive = true }
            }
        }

        OnBoardingSubScreen.Page6.name -> {
            viewModel.progress5.floatValue = 0f
            subNavController.navigate(OnBoardingSubScreen.Page5.name) {
                popUpTo(OnBoardingSubScreen.Page6.name) { inclusive = true }
            }
        }

    }
}

@Composable
fun RoundedTextIndicator(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .size(25.dp)
            .background(MaterialTheme.colorScheme.background, shape = CircleShape)
            .border(
                2.dp, MaterialTheme.colorScheme.onBackground, CircleShape
            )
            .padding(3.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

fun toPage3(viewModel: OnBoardingViewModel, navController: NavHostController) {
    viewModel.progress2.floatValue = 1f
    navController.navigate(OnBoardingSubScreen.Page3.name) {
        popUpTo(OnBoardingSubScreen.Page2.name) { inclusive = true }
    }
}

fun toPage4(viewModel: OnBoardingViewModel, navController: NavHostController) {
    viewModel.progress3.floatValue = 1f
    navController.navigate(OnBoardingSubScreen.Page4.name) {
        popUpTo(OnBoardingSubScreen.Page3.name) { inclusive = true }
    }
}

fun toPage5(viewModel: OnBoardingViewModel, navController: NavHostController) {
    viewModel.progress4.floatValue = 1f
    navController.navigate(OnBoardingSubScreen.Page5.name) {
        popUpTo(OnBoardingSubScreen.Page4.name) { inclusive = true }
    }
}

fun toPage6(viewModel: OnBoardingViewModel, navController: NavHostController) {
    viewModel.progress5.floatValue = 1f
    navController.navigate(OnBoardingSubScreen.Page6.name) {
        popUpTo(OnBoardingSubScreen.Page5.name) { inclusive = true }
    }
}


fun step12(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit, onSuccess: () -> Unit) {
    val data = PartnerOnBoardRequest(
        userId = "000002", onboardData = OnboardData(
            basicDetails = BasicDetails(
                pincode = viewModel.pincode.value,
                twoWheeler = viewModel.selected2Wheeler.value,
                address2 = viewModel.addressL2.value,
                city = viewModel.state.value,
                address1 = viewModel.addressL1.value,
                name = viewModel.fullName.value,
                mobile = viewModel.mobile.value,
                fourWheeler = viewModel.selected4Wheeler.value,
                state = viewModel.state.value,
                acceptedTC = viewModel.tcChecked.value
            )
        )
    )
    viewModel.onBoardUser(data, onSuccess, onFailure)
}

fun step3(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit, function: () -> Unit) {
    val data = PartnerOnBoardRequest(
        userId = "000002", onboardData = OnboardData(
            identityDetails = IdentityDetails(
                aadhaarNo = viewModel.aadharNumber.value,
                panNo = viewModel.panNumber.value,
                eleBillNo = viewModel.electricBillNumber.value,
                eleProvider = viewModel.electricProvide.value,
                otherDoc = "",
            )
        )
    )
    viewModel.onBoardUser(data, function, onFailure)
}

fun step4(
    context: Context,
    viewModel: OnBoardingViewModel,
    onFailure: (String) -> Unit,
    function: () -> Unit
) {
    viewModel.uploadDocImage(context, "000002", function, onFailure)
}

fun step5(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit, function: () -> Unit) {
    val data = PartnerOnBoardRequest(
        userId = "000002", onboardData = OnboardData(
            bankDetails = BankDetails(
                bankValue = viewModel.bankName.value,
                accNo = viewModel.accNumber.value,
                accHolderName = viewModel.accNameHolder.value,
                bankName = viewModel.bankName.value,
                ifscCode = viewModel.ifscCode.value,
            )
        )
    )
    viewModel.onBoardUser(data, function, onFailure)
}

fun step6(
    context: Context,
    viewModel: OnBoardingViewModel,
    onFailure: (String) -> Unit,
    function: () -> Unit
) {
    viewModel.uploadParkingImages(context, "000002", function, onFailure)
}

fun checkPage1(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit): Boolean {
    if (!viewModel.selected2Wheeler.value && !viewModel.selected4Wheeler.value) {
        onFailure("Please Select the type of vehicle service you want to provide")
        return false
    }

    if (!viewModel.tcChecked.value) {
        onFailure("Please accept the Terms and Conditions of our service.")
        return false
    }

    return true
}

fun checkPage2(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit): Boolean {

    if (viewModel.fullName.value.isNullOrBlank()) {
        onFailure("Please Enter Name")
        return false
    }

    if (viewModel.mobile.value.isNullOrBlank() && viewModel.mobile.value.length != 10) {
        onFailure("Please Enter Valid Mobile Number")
        return false
    }

    if (viewModel.addressL1.value.isNullOrBlank()) {
        onFailure("Please Enter Address Line 1")
        return false
    }

    if (viewModel.state.value.isNullOrBlank()) {
        onFailure("Please Enter State")
        return false
    }

    if (viewModel.pincode.value.isNullOrBlank() && viewModel.pincode.value.length != 6) {
        onFailure("Please Enter Valid Pincode")
        return false
    }

    return true
}

fun checkPage3(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit): Boolean {
    if (viewModel.aadharNumber.value.isEmpty()) {
        onFailure("Please Enter Your Aadhar Number")
        return false
    }

    if (viewModel.panNumber.value.isEmpty()) {
        onFailure("Please Enter Your Pan Number")
        return false
    }

    if (viewModel.electricProvide.value.isEmpty()) {
        onFailure("Please Select Your Electric Provider Name")
        return false
    }

    if (viewModel.electricBillNumber.value.isEmpty()) {
        onFailure("Please Enter Electric Bill Number")
        return false
    }

    return true
}

fun checkPage4(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit): Boolean {
    if (viewModel.aadharBackByteArray.value == null) {
        onFailure("Please Select Aadhar Card Back Image")
        return false
    }
    if (viewModel.aadharFrontByteArray.value == null) {
        onFailure("Please Select Aadhar Card Front Image")
        return false
    }

    if (viewModel.panByteArray.value == null) {
        onFailure("Please Select Pan Card Image")
        return false
    }

    if (viewModel.electricBillByteArray.value == null) {
        onFailure("Please Select Recent Electric Bill Image")
        return false
    }

    return true
}

fun checkPage5(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit): Boolean {
    if (viewModel.accNameHolder.value.isEmpty()) {
        onFailure("Please Enter Account Holder Name")
        return false
    }
    if (viewModel.bankName.value.isEmpty()) {
        onFailure("Please Select Your Provider Bank")
        return false
    }
    if (viewModel.accNumber.value.isEmpty()) {
        onFailure("Please Enter Your Account Number")
        return false
    }
    if (viewModel.accNumber.value != viewModel.accConfirmNumber.value) {
        onFailure("Please Confirm Your Account Number")
        return false
    }
    if (viewModel.ifscCode.value.isEmpty()) {
        onFailure("Please Enter IFSC Code")
        return false
    }
    return true
}

fun checkPage6(viewModel: OnBoardingViewModel, onFailure: (String) -> Unit): Boolean {
    if (viewModel.pkImage1ByteArray.value == null) {
        onFailure("Please Select 4 Image")
        return false
    }
    if (viewModel.pkImage2ByteArray.value == null) {
        onFailure("Please Select 4 Image")
        return false
    }
    if (viewModel.pkImage3ByteArray.value == null) {
        onFailure("Please Select 4 Image")
        return false
    }
    if (viewModel.pkImage4ByteArray.value == null) {
        onFailure("Please Select 4 Image")
        return false
    }
    return true
}

