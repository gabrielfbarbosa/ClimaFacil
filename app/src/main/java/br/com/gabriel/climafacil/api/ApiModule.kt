package br.com.gabriel.climafacil.api

import ClimaService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModule {
    //Deu boa: https://api.open-meteo.com/v1/ forecast?latitude=52.52&longitude=13.41&current=relative_humidity_2m&hourly=temperature_2m,precipitation_probability&daily=sunrise,sunset,uv_index_max&forecast_days=1
    //https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,relative_humidity_2m,is_day&hourly=temperature_2m,precipitation_probability&daily=sunrise,sunset,uv_index_max&forecast_days=3
    /*
    * ILha:
    *  https://api.open-meteo.com/v1/forecast?latitude=-20.4328&longitude=-51.3425&current=is_day&hourly=temperature_2m,relative_humidity_2m,precipitation_probability&daily=sunrise,sunset,uv_index_max&timezone=America%2FSao_Paulo
     */
    private const val BASE_URL = "https://api.open-meteo.com/v1/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val climaService: ClimaService by lazy {
        retrofit.create(ClimaService::class.java)
    }
}