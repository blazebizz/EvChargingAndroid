package com.blaze.data.startup.model.res

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

	@field:SerializedName("data")
	val data: CreateUserData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class CreateUserData(

	@field:SerializedName("authorization")
	val authorization: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mobileNo")
	val mobileNo: String? = null,

	@field:SerializedName("userType")
	val userType: String? = null
)
