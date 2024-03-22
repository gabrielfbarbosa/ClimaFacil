package br.com.gabriel.climafacil.apitres

import com.squareup.moshi.Json

data class ResponseCompleto(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Int,
    val current_units: Units,
    val current: Current,
    val hourly_units: Units,
    val hourly: Hourly,
    val daily_units: Units,
    val daily: Daily
) {
    data class Units(
        val time: String,
        val interval: String = "",
        @Json(name = "relative_humidity_2m") val relativeHumidity2m: String = "",
        @Json(name = "temperature_2m") val temperature2m: String = "",
        val precipitation_probability: String = ""
    )

    data class Current(
        val time: String,
        val interval: Int,
        @Json(name = "relative_humidity_2m") val relativeHumidity2m: Int
    )

    data class Hourly(
        val time: List<String>,
        @Json(name = "temperature_2m") val temperatures: List<Double>,
        val precipitation_probability: List<Int>
    )

    data class Daily(
        val time: List<String>,
        val sunrise: List<String>,
        val sunset: List<String>,
        @Json(name = "uv_index_max") val uvIndexMax: List<Double>
    )
}

