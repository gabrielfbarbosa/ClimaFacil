import br.com.gabriel.climafacil.api.model.Clima
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaService {
    @GET("forecast")
    suspend fun getPrevisao(
        @Query("latitude") latitude: Double = 0.0,
        @Query("longitude") longitude: Double = 0.0,
        @Query("current") current: String = "is_day",
        @Query("hourly") hourly: String = "temperature_2m,relative_humidity_2m,precipitation_probability",
        @Query("daily") daily: String = "sunrise,sunset,uv_index_max",
        @Query("timezone") timezone: String = "America/Sao_Paulo"
    ): Clima
}
