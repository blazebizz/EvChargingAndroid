package com.blaze.feature.onboarding.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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

    private val auth = repo.getAuth()
    val userId = auth.currentUser?.uid?:""

    val progress1 = mutableFloatStateOf(0f)
    val progress2 = mutableFloatStateOf(0f)
    val progress3 = mutableFloatStateOf(0f)
    val progress4 = mutableFloatStateOf(0f)
    val progress5 = mutableFloatStateOf(0f)

    //page1
//    val selected2Wheeler = mutableStateOf(false)
//    val selected4Wheeler = mutableStateOf(false)
    val tcChecked = mutableStateOf(false)

    //page2
    val fullName = mutableStateOf("")
    val mobile = mutableStateOf("")
    val addressL1 = mutableStateOf("")
    val addressL2 = mutableStateOf("")
    val state = mutableStateOf("")
    val city = mutableStateOf("")
    val pincode = mutableStateOf("")

    //page3
    val aadharNumber = mutableStateOf("")
    val panNumber = mutableStateOf("")
    val electricProvide = mutableStateOf("")
    val electricBillNumber = mutableStateOf("")

    //page4
    val aadharFrontByteArray = mutableStateOf<ByteArray?>(null)
    val aadharBackByteArray = mutableStateOf<ByteArray?>(null)
    val panByteArray = mutableStateOf<ByteArray?>(null)
    val electricBillByteArray = mutableStateOf<ByteArray?>(null)
    val otherByteArray = mutableStateOf<ByteArray?>(null)

    //page5
    val accNameHolder = mutableStateOf("")
    val bankName = mutableStateOf("")
    val accNumber = mutableStateOf("")
    val accConfirmNumber = mutableStateOf("")
    val ifscCode = mutableStateOf("")

/*    //page6
    val pkImage1ByteArray = mutableStateOf<ByteArray?>(null)
    val pkImage2ByteArray = mutableStateOf<ByteArray?>(null)
    val pkImage3ByteArray = mutableStateOf<ByteArray?>(null)
    val pkImage4ByteArray = mutableStateOf<ByteArray?>(null)*/

    val fetchedOnBoardUserData = mutableStateOf<FetchPartnerOnBoardDataResponse?>(null)

/*    fun fetchOnBoardUserData(
//        userId: String,
                             loading: MutableState<Boolean>) {
        loading.value = true
        fetchedOnBoardUserData.value = null
        val body = FetchPartnerOnBoardDataRequest(userId = userId)
        ioScope.launch {
            repo.fetchUserOnBoardData(body).handleFlow(onLoading = {
                loading.value = it
            }, onFailure = { _, _, _ -> }) {
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
    }*/

    private val uploadDocList =
        listOf("aadhaarFront", "aadhaarBack", "pan", "electricBill", "other")

    fun uploadDocImage(
        context: Context,
//        userId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
        loading: MutableState<Boolean>
    ) {
        val mutableList = mutableListOf<DocImage>()

        val currentIndex = mutableIntStateOf(0)
        uploadDoc(
            context,
            uploadDocList,
            currentIndex,
            mutableList,
            onSuccess,
            onFailure,
//            userId,
            loading
        )

        /*  uploadDocList.forEachIndexed { index, doc ->
              val imageUri = when (doc) {
                  "aadhaarFront" -> aadharFrontByteArray.value
                  "aadhaarBack" -> aadharBackByteArray.value
                  "pan" -> panByteArray.value
                  "electricBill" -> electricBillByteArray.value
                  "other" -> otherByteArray.value
                  else -> {
                      null
                  }
              }
              if (imageUri != null) {
                  repo.uploadImage(userId, doc, imageUri, onFailure = {
                      onFailure(it.toString())
                  }, onSuccess = {
                      mutableList.add(DocImage(doc, it))
                      Toast.makeText(
                          context, "${mutableList.size} image url: $it", Toast.LENGTH_SHORT
                      ).show()
                      if (index == uploadDocList.size - 1) {
                          Toast.makeText(
                              context, "${mutableList.size} image list", Toast.LENGTH_SHORT
                          ).show()
                          val body = PartnerOnBoardRequest(
                              userId = userId, onboardData = OnboardData(
                                  documentImage = mutableList
                              )
                          )
                          onBoardUser(body, onSuccess, onFailure)
                      }
                  })
              }
          }*/
    }

    fun onBoardUser(
        body: PartnerOnBoardRequest,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit = {},
        loading: MutableState<Boolean>
    ) {
//        ioScope.launch {
//            loading.value = true
//            repo.onBoard(body).handleFlow(onLoading = {
//                loading.value = it
//            }, onFailure = { msg, _, _ ->
//                onFailure(msg)
//            }, onSuccess = {
//                mainScope.launch {
//                    onSuccess()
//                }
//            })
//        }
        onSuccess()
    }

    fun uploadParkingImages(
        context: Context,
//        userId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
        loading: MutableState<Boolean>
    ) {
        ioScope.launch {


            val mutableList = mutableListOf<String?>()
            val currentIndex = mutableIntStateOf(0)
            uploadPark(
                context,
                uploadDocList,
                currentIndex,
                mutableList,
                onSuccess,
                onFailure,
//                userId,
                loading
            )

            /*   for (i in 0..3) {
                   val imageUri = when (i) {
                       0 -> pkImage1ByteArray.value
                       1 -> pkImage2ByteArray.value
                       2 -> pkImage3ByteArray.value
                       3 -> pkImage4ByteArray.value
                       else -> {
                           null
                       }
                   }

               }

               if (mutableList.isNotEmpty()) {
                   val body = PartnerOnBoardRequest(
                       userId = userId, onboardData = OnboardData(
                           parkingImage = mutableList
                       )
                   )
                   onBoardUser(body, onSuccess)
               } else {
                   onFailure("Please upload images")
               }*/
        }
    }

    private fun uploadDoc(
        context: Context,
        uploadDocList: List<String>,
        currentIndex: MutableState<Int>,
        mutableList: MutableList<DocImage>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
//        userId: String,
        loading: MutableState<Boolean>
    ) {
        val imageUri = when (uploadDocList[currentIndex.value]) {
            "aadhaarFront" -> aadharFrontByteArray.value
            "aadhaarBack" -> aadharBackByteArray.value
            "pan" -> panByteArray.value
            "electricBill" -> electricBillByteArray.value
            "other" -> otherByteArray.value
            else -> {
                null
            }
        }

        //uploading to firebase
        if (imageUri != null) {
            repo.uploadImage(userId, uploadDocList[currentIndex.value], imageUri, onFailure = {
                onFailure(it.toString())
            }, onSuccess = {
                mutableList.add(DocImage(uploadDocList[currentIndex.value], it))
                Toast.makeText(
                    context, "${mutableList.size} image url: $it", Toast.LENGTH_SHORT
                ).show()
                if (currentIndex.value == uploadDocList.size - 1) {
                    Toast.makeText(
                        context, "${mutableList.size} image list", Toast.LENGTH_SHORT
                    ).show()
                    val body = PartnerOnBoardRequest(
                        userId = userId, onboardData = OnboardData(
                            documentImage = mutableList
                        )
                    )
                    onBoardUser(body, onSuccess, onFailure, loading)
                    return@uploadImage
                }
                currentIndex.value = currentIndex.value + 1
                uploadDoc(
                    context,
                    uploadDocList,
                    currentIndex,
                    mutableList,
                    onSuccess,
                    onFailure,
//                    userId,
                    loading
                )

            })
        }
    }

    private fun uploadPark(
        context: Context,
        uploadDocList: List<String>,
        currentIndex: MutableIntState,
        mutableList: MutableList<String?>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
//        userId: String,
        loading: MutableState<Boolean>
    ) {
//        val imageUri = when (currentIndex.intValue) {
//            0 -> pkImage1ByteArray.value
//            1 -> pkImage2ByteArray.value
//            2 -> pkImage3ByteArray.value
//            3 -> pkImage4ByteArray.value
//            else -> {
//                null
//            }
//        }
//        if (imageUri != null) {
//            repo.uploadImage(userId, "parkingImage${currentIndex.intValue}", imageUri, onFailure = {
//                onFailure(it.toString())
//            }, onSuccess = {
//                mutableList.add(it)
//
//                if (currentIndex.intValue == 3) {
//                    Toast.makeText(
//                        context, "${mutableList.size} image list", Toast.LENGTH_SHORT
//                    ).show()
//                    val body = PartnerOnBoardRequest(
//                        userId = userId, onboardData = OnboardData(
//                            parkingImage = mutableList
//                        )
//                    )
//                    onBoardUser(body, onSuccess, onFailure, loading)
//                    return@uploadImage
//                }
//
//                currentIndex.intValue = currentIndex.intValue + 1
//                uploadPark(
//                    context,
//                    uploadDocList,
//                    currentIndex,
//                    mutableList,
//                    onSuccess,
//                    onFailure,
////                    userId,
//                    loading
//                )
//
//            })
//        }

    }
}