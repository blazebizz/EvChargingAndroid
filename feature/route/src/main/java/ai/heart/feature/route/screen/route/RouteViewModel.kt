package ai.heart.feature.route.screen.route

import ai.heart.data.route.repositories.RouteRepo
import androidx.lifecycle.ViewModel
import com.blaze.core.utils.util.ioScope
import com.velox.lazeir.utils.outlet.handleFlow
import com.velox.lazeir.utils.outlet.handleKtorFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val routeRepo: RouteRepo
) : ViewModel() {
    fun getDirection() {
        ioScope.launch {
            routeRepo.getDirection().handleKtorFlow(

                onSuccess = {},
                onLoading = {

                },
                onFailure = { _, _, _ ->

                }
            )
        }
    }
}