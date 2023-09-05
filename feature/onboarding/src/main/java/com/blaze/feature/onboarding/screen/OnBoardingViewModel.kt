package com.blaze.feature.onboarding.screen

import android.net.Uri
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.mainScope
import com.blaze.data.onboarding.model.req.DocImage
import com.blaze.data.onboarding.model.req.FetchPartnerOnBoardDataRequest
import com.blaze.data.onboarding.model.req.OnboardData
import com.blaze.data.onboarding.model.req.PartnerOnBoardRequest
import com.blaze.data.onboarding.model.res.FetchPartnerOnBoardDataResponse
import com.blaze.data.onboarding.repositories.OnBoardingRepo
import com.velox.lazeir.utils.outlet.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val repo: OnBoardingRepo) : ViewModel() {


    val progress1 = mutableFloatStateOf(0f)
    val progress2 = mutableFloatStateOf(0f)
    val progress3 = mutableFloatStateOf(0f)
    val progress4 = mutableFloatStateOf(0f)
    val progress5 = mutableFloatStateOf(0f)

    //page1
    val selected2Wheeler = mutableStateOf(false)
    val selected4Wheeler = mutableStateOf(false)
    val tcChecked = mutableStateOf(false)

    //page2
    val fullName = mutableStateOf("")
    val mobile = mutableStateOf("")
    val addressL1 = mutableStateOf("")
    val addressL2 = mutableStateOf("")
    val state = mutableStateOf("")
    val pincode = mutableStateOf("")

    //page3
    val aadharNumber = mutableStateOf("")
    val panNumber = mutableStateOf("")
    val electricProvide = mutableStateOf("")
    val electricBillNumber = mutableStateOf("")

    //page4
    val aadharFrontUri = mutableStateOf<Uri?>(null)
    val aadharBackUri = mutableStateOf<Uri?>(null)
    val panUri = mutableStateOf<Uri?>(null)
    val electricBillUri = mutableStateOf<Uri?>(null)
    val otherUri = mutableStateOf<Uri?>(null)

    //page5
    val accNameHolder = mutableStateOf("")
    val bankName = mutableStateOf("")
    val accNumber = mutableStateOf("")
    val accConfirmNumber = mutableStateOf("")
    val ifscCode = mutableStateOf("")

    //page6
    val pkImage1 = mutableStateOf<Uri?>(null)
    val pkImage2 = mutableStateOf<Uri?>(null)
    val pkImage3 = mutableStateOf<Uri?>(null)
    val pkImage4 = mutableStateOf<Uri?>(null)

    val fetchedOnBoardUserData = mutableStateOf<FetchPartnerOnBoardDataResponse?>(null)


    fun fetchOnBoardUserData(userId: String) {
        val body = FetchPartnerOnBoardDataRequest(userId = userId)
        ioScope.launch {
            repo.fetchUserOnBoardData(body).handleFlow(onLoading = {}, onFailure = { _, _, _ -> }) {
                fetchedOnBoardUserData.value = it

                if (it.data?.isEmpty() == false) {
                    if (it.data?.get(0)?.onboardData?.basicDetails != null) {
                        selected2Wheeler.value =
                            it.data?.get(0)?.onboardData?.basicDetails?.twoWheeler ?: false
                        selected4Wheeler.value =
                            it.data?.get(0)?.onboardData?.basicDetails?.fourWheeler ?: false
                        fullName.value = it.data?.get(0)?.onboardData?.basicDetails?.name ?: ""
                        mobile.value = it.data?.get(0)?.onboardData?.basicDetails?.mobile ?: ""
                        addressL1.value = it.data?.get(0)?.onboardData?.basicDetails?.address1 ?: ""
                        addressL2.value = it.data?.get(0)?.onboardData?.basicDetails?.address2 ?: ""
                        state.value = it.data?.get(0)?.onboardData?.basicDetails?.state ?: ""
                        pincode.value = it.data?.get(0)?.onboardData?.basicDetails?.pincode ?: ""
                    }

                    if (it.data?.get(0)?.onboardData?.identityDetails != null) {
                        aadharNumber.value =
                            it.data?.get(0)?.onboardData?.identityDetails?.aadhaarNo ?: ""
                        panNumber.value = it.data?.get(0)?.onboardData?.identityDetails?.panNo ?: ""
                        electricProvide.value =
                            it.data?.get(0)?.onboardData?.identityDetails?.eleProvider ?: ""
                        electricBillNumber.value =
                            it.data?.get(0)?.onboardData?.identityDetails?.eleBillNo ?: ""
                    }
                    if (it.data?.get(0)?.onboardData?.bankDetails != null) {
                        accNameHolder.value =
                            it.data?.get(0)?.onboardData?.bankDetails?.accHolderName ?: ""

                    }
                }

            }
        }
    }

    private val uploadDocList =
        listOf("aadhaarFront", "aadhaarBack", "pan", "electricBill", "other")

    fun uploadDocImage(userId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val mutableList = mutableListOf<DocImage>()
        for (doc in uploadDocList) {
            val imageUri = when (doc) {
                "aadhaarFront" -> aadharFrontUri.value
                "aadhaarBack" -> aadharBackUri.value
                "pan" -> panUri.value
                "electricBill" -> electricBillUri.value
                "other" -> otherUri.value
                else -> {
                    null
                }
            }
            if (imageUri != null) {
                repo.uploadImage(userId, doc, imageUri, onFailure = {

                }, onSuccess = {
                    mutableList.add(DocImage(doc, it))
                })
            }
        }

        val body = PartnerOnBoardRequest(
            userId = userId, onboardData = OnboardData(
                documentImage = mutableList
            )
        )
        onBoardUser(body, onSuccess, onFailure)
    }

    fun onBoardUser(
        body: PartnerOnBoardRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit = {}
    ) {
        ioScope.launch {
            repo.onBoard(body).handleFlow(onLoading = {}, onFailure = { msg, _, _ ->
                onFailure(msg)
            }, onSuccess = {
                mainScope.launch {
                    onSuccess()
                }
            })
        }
    }

    fun uploadParkingImages(userId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        ioScope.launch {
            val mutableList = mutableListOf<String?>()
            for (i in 0..3) {
                val imageUri = when (i) {
                    0 -> pkImage1.value
                    1 -> pkImage2.value
                    2 -> pkImage3.value
                    3 -> pkImage4.value
                    else -> {
                        null
                    }
                }
                if (imageUri != null) {
                    repo.uploadImage(userId, i.toString(), imageUri, onFailure = {
                        onFailure(it.toString())
                    }, onSuccess = {
                        mutableList.add(it)
                    })
                }
            }

            val body = PartnerOnBoardRequest(
                userId = userId, onboardData = OnboardData(
                    parkingImage = mutableList
                )
            )
            onBoardUser(body, onSuccess)
        }
    }
}