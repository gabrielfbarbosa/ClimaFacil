package br.com.gabriel.climafacil.apiCORRETA

import ClimaService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    companion object {
        private var apiService: ClimaService? = null
        private const val BASE_URL = "https://api.open-meteo.com/v1/"

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getInstance(): ClimaService {

            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                    .create(ClimaService::class.java)
            }

            return apiService!!
        }
    }
}