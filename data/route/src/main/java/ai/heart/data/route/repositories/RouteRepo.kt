package ai.heart.data.route.repositories

import com.velox.lazeir.utils.handler.NetworkResourceKtor
import kotlinx.coroutines.flow.Flow

interface RouteRepo {
    fun getDirection(): Flow<NetworkResourceKtor<Any>>
}