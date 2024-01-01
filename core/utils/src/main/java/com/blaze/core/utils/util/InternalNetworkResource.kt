package com.blaze.core.utils.util

import org.json.JSONObject

/**
 * Author: [cr velox]
 * */
sealed class InternalNetworkResource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorObject: JSONObject? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : InternalNetworkResource<T>(data)

    class Error<T>(message: String, errorObject: JSONObject? = JSONObject(), code: Int? = -100) :
        InternalNetworkResource<T>(null, message, errorObject, code)

    class Loading<T>(val isLoading: Boolean) : InternalNetworkResource<T>(null)

}