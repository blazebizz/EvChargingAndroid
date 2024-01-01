package com.blaze.data.startup.model.req

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mobileno")
	val mobileno: String? = null,

	@field:SerializedName("userID")
	val userID: String? = null
)
