import br.com.gabriel.climafacil.apitres.ResponseCompleto
import retrofit2.http.GET

interface ClimaServiceAPITRES {
    @GET("forecast?latitude=52.52&longitude=13.41&current=relative_humidity_2m&hourly=temperature_2m,precipitation_probability&daily=sunrise,sunset,uv_index_max&forecast_days=1")
    suspend fun getPrevisao(): ResponseCompleto //PrevisaoResponseAPITRES
}

//forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m&forecast_days=1