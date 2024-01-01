package com.blaze.core.utils.util

import com.velox.lazeir.utils.handler.NetworkResource
import com.velox.lazeir.utils.outlet.handleFlow
import com.velox.lazeir.utils.outlet.handleNetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException



fun <T> Response<T>.handleNetworkResponseInternal(): Flow<NetworkResource<T>> {
    return flow {
        emit(NetworkResource.Loading(isLoading = true))
        try {
            if (this@handleNetworkResponseInternal.isSuccessful) {
                emit(NetworkResource.Success(this@handleNetworkResponseInternal.body()))
            } else {
                val code = this@handleNetworkResponseInternal.code()
                val errorBody = this@handleNetworkResponseInternal.errorBody()?.string()
                try {
                    val jObjError = errorBody?.let { JSONObject(it) }
                    emit(NetworkResource.Error("Network Error", jObjError, code))
                } catch (e: Exception) {
                    emit(NetworkResource.Error("UNKNOWN ERROR", code = code))
                }
            }
        } catch (e: TimeoutException) {
            e.message?.let { emit(NetworkResource.Error("Time Out")) }
        } catch (e: SocketTimeoutException) {
            e.message?.let { emit(NetworkResource.Error("Time Out")) }
        } catch (e: IOException) {
            e.message?.let { emit(NetworkResource.Error(it)) }
        } catch (e: IllegalStateException) {
            e.message?.let { emit(NetworkResource.Error(it)) }
        } catch (e: NullPointerException) {
            e.message?.let { emit(NetworkResource.Error(it)) }
        } catch (e: Exception) {
            e.message?.let { emit(NetworkResource.Error(it)) }
        } finally {
            emit(NetworkResource.Loading(isLoading = false))
        }
        emit(NetworkResource.Loading(isLoading = false))
    }.flowOn(Dispatchers.IO)
}


/**
 * [handleFlow] takes the response from use case function as Resource<> with in Main Coroutine Scope
 * return the extracted response with in onLoading(),onFailure(),onSuccess()
 * Call within IO Scope
 * **/

suspend fun <T> Flow<NetworkResource<T>>.handleFlowInt(
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String, errorObject: JSONObject, code: Int) -> Unit,
    onSuccess: suspend (it: T) -> Unit
) {
    return handleFlowInternal(this, onLoading, onFailure, onSuccess)
}

suspend fun <T> handleFlowInternal(
    flow: Flow<NetworkResource<T>>,
    onLoading: suspend (it: Boolean) -> Unit,
    onFailure: suspend (it: String, errorObject: JSONObject, code: Int) -> Unit,
    onSuccess: suspend (it: T) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        flow.collectLatest {
            when (it) {
                is NetworkResource.Error -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onFailure.invoke(it.message!!, it.errorObject!!, it.code!!)
                    }
                }

                is NetworkResource.Loading -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onLoading.invoke(it.isLoading)
                    }
                }

                is NetworkResource.Success -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        onSuccess.invoke(it.data!!)
                    }
                }
            }
        }
    }
}




