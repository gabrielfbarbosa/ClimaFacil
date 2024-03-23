import br.com.gabriel.climafacil.api.ClimaResponse
import retrofit2.http.GET

interface ClimaService {
    @GET("forecast?latitude=-20.4328&longitude=-51.3425&current=is_day&hourly=temperature_2m,relative_humidity_2m,precipitation_probability&daily=sunrise,sunset,uv_index_max&timezone=America%2FSao_Paulo")
    suspend fun getPrevisao(): ClimaResponse //PrevisaoResponseAPITRES
}

//forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m&forecast_days=1