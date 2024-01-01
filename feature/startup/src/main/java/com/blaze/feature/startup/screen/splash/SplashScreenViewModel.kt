package com.blaze.feature.startup.screen.splash

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.blaze.core.utils.util.handleFlowInt
import com.blaze.core.utils.util.ioScope
import com.blaze.core.utils.util.mainScope
import com.blaze.data.startup.model.req.GenerateTokenRequest
import com.blaze.data.startup.model.res.GenerateTokenResponse
import com.blaze.data.startup.repositories.StartUpRepo
import com.google.firebase.auth.FirebaseAuth
import com.velox.lazeir.utils.outlet.handleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(auth: FirebaseAuth, private val repo: StartUpRepo) : ViewModel() {
    val firebaseAuth = auth

    fun generateToken(
        body: GenerateTokenRequest,
        onSuccess: (res: GenerateTokenResponse) -> Unit,
        onFailure: (String, Int) -> Unit = { _, _ -> },
        loading: MutableState<Boolean>
    ) {
        ioScope.launch {
            loading.value = true
            repo.generateToken(body).handleFlowInt(onLoading = {
                loading.value = it
            }, onFailure = { msg, obj, _ ->
                var message: String = msg
                var status = -1
                runCatching {
                    message = obj.getString("message")
                    status = obj.getInt("status")
                }.onFailure {
                    message = msg
                }
                loading.value = false
                onFailure(message, status)
            }, onSuccess = {
                mainScope.launch {
                    onSuccess(it)
                }
            })
        }
    }
}