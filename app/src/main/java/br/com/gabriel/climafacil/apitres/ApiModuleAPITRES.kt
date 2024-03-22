package br.com.gabriel.climafacil.apitres

import ClimaServiceAPITRES
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModuleAPITRES {
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

    val climaService: ClimaServiceAPITRES by lazy {
        retrofit.create(ClimaServiceAPITRES::class.java)
    }
}