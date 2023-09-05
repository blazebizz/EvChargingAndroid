package com.blaze.data.onboarding.model.req

import com.google.gson.annotations.SerializedName

data class PartnerOnBoardRequest(

    @field:SerializedName("onboardData") val onboardData: OnboardData? = null,

    @field:SerializedName("userId") val userId: String? = null
)

data class OnboardData(

	@field:SerializedName("bankDetails") val bankDetails: BankDetails? = null,

	@field:SerializedName("basicDetails") val basicDetails: BasicDetails? = null,

	@field:SerializedName("parkingImage") val parkingImage: List<String?>? = null,

	@field:SerializedName("identityDetails") val identityDetails: IdentityDetails? = null,

	@field:SerializedName("documentImage") val documentImage: List<DocImage?>? = null,
)

data class DocImage(
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("value") val value: String? = null,
    )

data class BasicDetails(

    @field:SerializedName("pincode") val pincode: String? = null,

    @field:SerializedName("twoWheeler") val twoWheeler: Boolean? = null,

    @field:SerializedName("address2") val address2: String? = null,

    @field:SerializedName("city") val city: String? = null,

    @field:SerializedName("address1") val address1: String? = null,

    @field:SerializedName("name") val name: String? = null,

    @field:SerializedName("mobile") val mobile: String? = null,

    @field:SerializedName("fourWheeler") val fourWheeler: Boolean? = null,

    @field:SerializedName("state") val state: String? = null
)

data class BankDetails(

    @field:SerializedName("bankValue") val bankValue: String? = null,

    @field:SerializedName("accNo") val accNo: String? = null,

    @field:SerializedName("accHolderName") val accHolderName: String? = null,

    @field:SerializedName("bankName") val bankName: String? = null,

    @field:SerializedName("ifscCode") val ifscCode: String? = null
)

data class IdentityDetails(

    @field:SerializedName("aadhaarNo") val aadhaarNo: String? = null,

    @field:SerializedName("panNo") val panNo: String? = null,

    @field:SerializedName("eleBillNo") val eleBillNo: String? = null,

    @field:SerializedName("eleProvider") val eleProvider: String? = null,

    @field:SerializedName("otherDoc") val otherDoc: String? = null
)
