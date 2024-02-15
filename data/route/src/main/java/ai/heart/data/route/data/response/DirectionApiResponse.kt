package ai.heart.data.route.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DirectionApiResponse(
    @SerialName("geocoded_waypoints")
    val geocodedWaypoints: List<GeocodedWaypoint?>?,
    @SerialName("routes")
    val routes: List<Route?>?,
    @SerialName("status")
    val status: String?
) {
    @Keep
    @Serializable
    data class GeocodedWaypoint(
        @SerialName("geocoder_status")
        val geocoderStatus: String?,
        @SerialName("place_id")
        val placeId: String?,
        @SerialName("types")
        val types: List<String?>?
    )

    @Keep
    @Serializable
    data class Route(
        @SerialName("bounds")
        val bounds: Bounds?,
        @SerialName("copyrights")
        val copyrights: String?,
        @SerialName("legs")
        val legs: List<Leg?>?,
        @SerialName("overview_polyline")
        val overviewPolyline: OverviewPolyline?,
        @SerialName("summary")
        val summary: String?,
        @SerialName("warnings")
        val warnings: List<Any?>?,
        @SerialName("waypoint_order")
        val waypointOrder: List<Int?>?
    ) {
        @Keep
        @Serializable
        data class Bounds(
            @SerialName("northeast")
            val northeast: Northeast?,
            @SerialName("southwest")
            val southwest: Southwest?
        ) {
            @Keep
            @Serializable
            data class Northeast(
                @SerialName("lat")
                val lat: Double?,
                @SerialName("lng")
                val lng: Double?
            )

            @Keep
            @Serializable
            data class Southwest(
                @SerialName("lat")
                val lat: Double?,
                @SerialName("lng")
                val lng: Double?
            )
        }

        @Keep
        @Serializable
        data class Leg(
            @SerialName("distance")
            val distance: Distance?,
            @SerialName("duration")
            val duration: Duration?,
            @SerialName("end_address")
            val endAddress: String?,
            @SerialName("end_location")
            val endLocation: EndLocation?,
            @SerialName("start_address")
            val startAddress: String?,
            @SerialName("start_location")
            val startLocation: StartLocation?,
            @SerialName("steps")
            val steps: List<Step?>?,
            @SerialName("traffic_speed_entry")
            val trafficSpeedEntry: List<Any?>?,
            @SerialName("via_waypoint")
            val viaWaypoint: List<Any?>?
        ) {
            @Keep
            @Serializable
            data class Distance(
                @SerialName("text")
                val text: String?,
                @SerialName("value")
                val value: Int?
            )

            @Keep
            @Serializable
            data class Duration(
                @SerialName("text")
                val text: String?,
                @SerialName("value")
                val value: Int?
            )

            @Keep
            @Serializable
            data class EndLocation(
                @SerialName("lat")
                val lat: Double?,
                @SerialName("lng")
                val lng: Double?
            )

            @Keep
            @Serializable
            data class StartLocation(
                @SerialName("lat")
                val lat: Double?,
                @SerialName("lng")
                val lng: Double?
            )

            @Keep
            @Serializable
            data class Step(
                @SerialName("distance")
                val distance: Distance?,
                @SerialName("duration")
                val duration: Duration?,
                @SerialName("end_location")
                val endLocation: EndLocation?,
                @SerialName("html_instructions")
                val htmlInstructions: String?,
                @SerialName("maneuver")
                val maneuver: String?,
                @SerialName("polyline")
                val polyline: Polyline?,
                @SerialName("start_location")
                val startLocation: StartLocation?,
                @SerialName("travel_mode")
                val travelMode: String?
            ) {
                @Keep
                @Serializable
                data class Distance(
                    @SerialName("text")
                    val text: String?,
                    @SerialName("value")
                    val value: Int?
                )

                @Keep
                @Serializable
                data class Duration(
                    @SerialName("text")
                    val text: String?,
                    @SerialName("value")
                    val value: Int?
                )

                @Keep
                @Serializable
                data class EndLocation(
                    @SerialName("lat")
                    val lat: Double?,
                    @SerialName("lng")
                    val lng: Double?
                )

                @Keep
                @Serializable
                data class Polyline(
                    @SerialName("points")
                    val points: String?
                )

                @Keep
                @Serializable
                data class StartLocation(
                    @SerialName("lat")
                    val lat: Double?,
                    @SerialName("lng")
                    val lng: Double?
                )
            }
        }

        @Keep
        @Serializable
        data class OverviewPolyline(
            @SerialName("points")
            val points: String?
        )
    }
}