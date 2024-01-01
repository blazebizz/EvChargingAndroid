package com.blaze.feature.startup.screen.createuser

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.blaze.core.utils.util.handleFlowInt
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.mainScope
import com.blaze.data.startup.model.req.CreateUserRequest
import com.blaze.data.startup.model.res.CreateUserResponse
import com.blaze.data.startup.repositories.StartUpRepo
import com.velox.lazeir.utils.outlet.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val repo: StartUpRepo
) : ViewModel() {


    fun createUser(
        body: CreateUserRequest,
        onSuccess: (res: CreateUserResponse) -> Unit,
        onFailure: (String) -> Unit = {},
        loading: MutableState<Boolean>
    ) {
        ioScope.launch {
            loading.value = true
            repo.createUser(body).handleFlowInt(onLoading = {
                loading.value = it
            }, onFailure = { msg, obj, _ ->
                var message:String =msg
                runCatching {
                    message = obj.getString("message")
                }.onFailure {
                    message = msg
                }
                onFailure(message)
            }, onSuccess = {
                mainScope.launch {
                    onSuccess(it)
                }
            })
        }
    }
}