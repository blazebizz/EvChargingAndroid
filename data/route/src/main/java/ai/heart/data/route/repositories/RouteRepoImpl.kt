package ai.heart.data.route.repositories

import com.blaze.core.utils.BuildConfig
import com.velox.lazeir.utils.handler.KtorResource
import com.velox.lazeir.utils.handler.NetworkResourceKtor
import com.velox.lazeir.utils.outlet.handleNetworkResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RouteRepoImpl @Inject constructor(private val client: HttpClient) : RouteRepo {

    override fun getDirection(): Flow<KtorResource<Any>> {

        return handleNetworkResponse {
            client.get("https://maps.googleapis.com/maps/api/directions/json") {
//                parameter("origin", "${origin.first},${origin.second}")
//                parameter("destination", "${destination.first},${destination.second}")
//                parameter("key", BuildConfig.BASE_URL)

                parameter("origin", "20.324006, 85.809360")
                parameter("destination", "20.336403, 85.827476")
//                parameter("key", "crysgsL34UN7rJNo_V5BbQDiId0=")
                parameter("key", "AIzaSyDmfD3bg8q9YPNLY2wQNJxm0xWQObdnvjk")
//                parameter("key", BuildConfig.MAPS_API_KEY)
            }
        }

    }

}

/*
* {
  "error_message": "This IP, site or mobile application is not authorized to use this API key. Request received from IP address 2405:201:a003:72f3:9816:f083:5243:58ee, with empty referer",
  "routes": [],
  "status": "REQUEST_DENIED"
}
* */