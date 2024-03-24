import br.com.gabriel.climafacil.apiCORRETA.modelCORRETA.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaService { //Esse 'e a IApiService
    @GET("forecast")//forecast?latitude={latitude}&longitude={longitude}&current=is_day&hourly=temperature_2m,relative_humidity_2m,precipitation_probability&daily=sunrise,sunset,uv_index_max&timezone=America%2FSao_Paulo
    suspend fun getPrevisao(
        @Query("latitude") latitude: Double = 0.0,
        @Query("longitude") longitude: Double = 0.0,
        @Query("current") current: String = "is_day",
        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,precipitation_probability",
        @Query("daily") daily: String = "sunrise,sunset,uv_index_max",
        @Query("timezone") timezone: String = "America/Sao_Paulo"
    ): Weather
}
//WeatherResult//PrevisaoResponse //PrevisaoResponseAPITRES
//        @Query ("latitude") latitude: Double = 0.0,
//        @Query ("longitude") longitude: Double = 0.0

//forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m&forecast_days=1