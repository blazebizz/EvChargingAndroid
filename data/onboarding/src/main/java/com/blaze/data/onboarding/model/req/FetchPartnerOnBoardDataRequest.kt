package com.blaze.data.onboarding.model.req

import com.google.gson.annotations.SerializedName

data class FetchPartnerOnBoardDataRequest(

	@field:SerializedName("userId")
	val userId: String? = null
)
