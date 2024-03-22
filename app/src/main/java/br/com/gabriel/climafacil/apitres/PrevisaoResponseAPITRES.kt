import com.squareup.moshi.Json

data class PrevisaoResponseAPITRES(
    val hourly: Hourly
) {
    data class Hourly(
        @Json(name = "temperature_2m") val temperatures: List<Double>,
        @Json(name = "time") val time: List<String>

    )
}