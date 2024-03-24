package br.com.gabriel.climafacil.apiCORRETA.modelCORRETA

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Weather(
    @Json(name = "latitude") val latitude: Double? = null,
    @Json(name = "longitude") val longitude: Double? = null,
    @Json(name = "generationtime_ms") val generationtime_ms: Double? = null,
    @Json(name = "utc_offset_seconds") val utc_offset_seconds: Int? = null,
    @Json(name = "timezone") val timezone: String? = null,
    @Json(name = "timezone_abbreviation") val timezone_abbreviation: String? = null,
    @Json(name = "elevation") val elevation: Int? = null,
    @Json(name = "current_units") val current_units: CurrentUnits? = CurrentUnits(),
    @Json(name = "current") val current: Current? = Current(),
    @Json(name = "hourly_units") val hourly_units: HourlyUnits? = HourlyUnits(),
    @Json(name = "hourly") val hourly: Hourly? = Hourly(),
    @Json(name = "daily_units") val daily_units: DailyUnits? = DailyUnits(),
    @Json(name = "daily") val daily: Daily? = Daily()
) {
    data class CurrentUnits(
        @Json(name = "time") val time: String? = null,
        @Json(name = "interval") val interval: String? = null,
        @Json(name = "is_day")  val is_day: String? = null,
    )

    data class Current(
        @Json(name = "time")  val time: String? = null,
        @Json(name = "interval")   val interval: Int? = null,
        @Json(name = "is_day")  val is_day: Int? = null
    )
    data class HourlyUnits(
        @Json(name = "time") val time: String? = null,
        @Json(name = "temperature_2m")  val temperature2m: String? = null,
        @Json(name = "relative_humidity_2m")  val relative_humidity_2m: String? = null,
        @Json(name = "precipitation_probability")  val precipitation_prbability: String? = null,
    )

    data class Hourly(
        @Json(name = "time")  val time: List<String>? = emptyList(),
        @Json(name = "temperature_2m") val temperatures: List<Double>? = emptyList(),
        @Json(name = "relative_humidity_2m")  val relative_humidity_2m: List<Int>? = emptyList(),
        @Json(name = "precipitation_probability")  val precipitation_probability: List<Int>? = emptyList()
    )

    data class DailyUnits(
        @Json(name = "time") val time: String? = null,
        @Json(name = "sunrise") val sunrise: String? = null,
        @Json(name = "sunset")  val sunset: String? = null,
        @Json(name = "uv_index_max")  val uv_index_max: String? = null,
    )

    data class Daily(
        @Json(name = "time")  val time: List<String>? = emptyList(),
        @Json(name = "sunrise")  val sunrise: List<String>? = emptyList(),
        @Json(name = "sunset")  val sunset: List<String>? = emptyList(),
        @Json(name = "uv_index_max")  val uv_index_max: List<Double>? = emptyList()
    )
}


