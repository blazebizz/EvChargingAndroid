package com.blaze.data.onboarding.model.res

import com.google.gson.annotations.SerializedName

data class PartnerOnBoardResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null

)
