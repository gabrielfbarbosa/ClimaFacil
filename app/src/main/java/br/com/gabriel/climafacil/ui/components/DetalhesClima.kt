package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.gabriel.climafacil.apiCORRETA.modelCORRETA.Weather
import br.com.gabriel.climafacil.ui.components.info.CardInformacao
import br.com.gabriel.climafacil.utils.ClimaUtils

@Composable
fun DetalhesClima(weatherResponse: Weather) {
    val indexHourly = ClimaUtils().obterIndiceDadosAtuais(weatherResponse)
    val indexDaily =  ClimaUtils().obterIndiceDadosDiarios(weatherResponse)

    val currentUmidityUnit = weatherResponse.hourly_units?.relative_humidity_2m ?: ""
    val currentUmidity = indexHourly?.let { weatherResponse.hourly?.relative_humidity_2m?.get(it) }?: "-"
//    val dataHoraAtual = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault()).format(Date())

    val currentUv = indexDaily?.let { weatherResponse.daily?.uv_index_max?.get(it) }
    val currentUvUnit = weatherResponse.daily_units?.uv_index_max ?: ""

    val precProb = indexHourly?.let { weatherResponse.hourly?.precipitation_probability?.get(it) }?: "-"
    val precProbUnit = weatherResponse.hourly_units?.precipitation_prbability ?: ""

    val sunrieTime = indexDaily?.let {
        weatherResponse.daily?.sunrise?.get(it)
        ?.let { ClimaUtils().formatarHora (it) }
    }

    val sunsetTime = indexDaily?.let {
        weatherResponse.daily?.sunset?.get(it)
            ?.let { ClimaUtils().formatarHora (it) }
    }

    var diaNoite = if (weatherResponse.current?.is_day == 1) "Dia" else "Noite"

    Column()
    {
        Row {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Umidade", "$currentUmidity $currentUmidityUnit")
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Índice UV", "$currentUv $currentUvUnit")
            }
        }

        Box{
            CardInformacao("Prob. de Precipitacao", "$precProb $precProbUnit")
        }

        Row {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Nascer do Sol", "$sunrieTime hs")
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Ponhar do sol", "$sunsetTime hs")
            }
        }

        Box{
            CardInformacao("Dia ou noite", diaNoite)
        }
    }
}

//@Preview
//@Composable
//private fun DetalhesClimaPrev() {
//    ClimaFacilTheme {
//        Surface {
//            DetalhesClima()
//        }
//    }
//}