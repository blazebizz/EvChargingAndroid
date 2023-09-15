package com.blaze.data.onboarding.model.res

import com.google.gson.annotations.SerializedName

data class FetchPartnerOnBoardDataResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("onboardData")
	val onboardData: OnboardData? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

//	@field:SerializedName("reject_list")
//	val rejectList: List<String?>? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("remark")
	val remark: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class BankDetails(

	@field:SerializedName("accNo")
	val accNo: String? = null,

	@field:SerializedName("bankValue")
	val bankValue: String? = null,

	@field:SerializedName("accHolderName")
	val accHolderName: String? = null,

	@field:SerializedName("bankName")
	val bankName: String? = null,

	@field:SerializedName("ifscCode")
	val ifscCode: String? = null
)

data class IdentityDetails(

	@field:SerializedName("aadhaarNo")
	val aadhaarNo: String? = null,

	@field:SerializedName("panNo")
	val panNo: String? = null,

	@field:SerializedName("eleBillNo")
	val eleBillNo: String? = null,

	@field:SerializedName("eleProvider")
	val eleProvider: String? = null
)

data class BasicDetails(

	@field:SerializedName("pincode")
	val pincode: String? = null,

	@field:SerializedName("twoWheeler")
	val twoWheeler: Boolean? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("address2")
	val address2: String? = null,

	@field:SerializedName("address1")
	val address1: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("fourWheeler")
	val fourWheeler: Boolean? = null,

	@field:SerializedName("state")
	val state: String? = null
)

data class DocumentImage(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)



data class OnboardData(

	@field:SerializedName("bankDetails")
	val bankDetails: BankDetails? = null,

	@field:SerializedName("documentImage")
	val documentImage: List<DocumentImage>? = null,

	@field:SerializedName("basicDetails")
	val basicDetails: BasicDetails? = null,

	@field:SerializedName("parkingDetails")
	val parkingDetails: List<String?>? = null,

	@field:SerializedName("identityDetails")
	val identityDetails: IdentityDetails? = null
)
