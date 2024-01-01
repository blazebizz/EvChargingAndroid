package com.blaze.data.startup.model.req

import com.google.gson.annotations.SerializedName

data class GenerateTokenRequest(

	@field:SerializedName("userId")
	val userId: String? = null
)
