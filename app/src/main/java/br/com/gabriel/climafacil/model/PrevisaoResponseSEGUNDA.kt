import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrevisaoResponseSEGUNDA(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double?,
    val utc_offset_seconds: Int?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val elevation: Double?,
    val current_units: CurrentUnits?,
    val current: Current?,
    val hourly_units: HourlyUnits?,
    val hourly: Hourly?,
    val daily_units: DailyUnits?,
    val daily: List<Daily>?,
    ) {
    @JsonClass(generateAdapter = true)
    data class CurrentUnits(
        val time: String,
        val interval: String,
        val temperature_2m: String,
        val relative_humidity_2m: String,
        val is_day: String?
    )
    @JsonClass(generateAdapter = true)
    data class Current(
        val time: String,
        val interval: Int,
        val temperature_2m: Double,
        val relative_humidity_2m: Int,
        val is_day: Int
    )
    @JsonClass(generateAdapter = true)
    data class HourlyUnits(
        val time: String,
        val temperature_2m: String,
        val precipitation_probability: String
    )
    @JsonClass(generateAdapter = true)
    data class Hourly(
        val time: List<String>,
        val temperature_2m: List<Double>,
        val precipitation_probability: List<Int>
    )
    @JsonClass(generateAdapter = true)
    data class DailyUnits(
        val time: String,
        val sunrise: String,
        val sunset: String,
        val uv_index_max: String
    )
    @JsonClass(generateAdapter = true)
    data class Daily(
        val time: List<String>,
        val sunrise: List<String>,
        val sunset:  List<String>,
        val uv_index_max: List<Double>,
    )
}