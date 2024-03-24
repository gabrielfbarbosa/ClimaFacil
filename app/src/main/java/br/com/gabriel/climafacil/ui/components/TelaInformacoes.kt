package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.apiCORRETA.modelCORRETA.Weather
import br.com.gabriel.climafacil.model.sampleDados
import br.com.gabriel.climafacil.ui.components.info.DadosAtuais
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import br.com.gabriel.climafacil.utils.ClimaUtils
import java.util.Calendar

@Composable
fun TelaInformacoes(weatherResponse: Weather) {
    val indexTemp = ClimaUtils().obterIndiceDadosAtuais(weatherResponse)
    val currentTemperature = indexTemp?.let { weatherResponse.hourly?.temperatures?.get(it) }?: 0.0
    val currentTemperatureUnit = weatherResponse.hourly_units?.temperature2m ?: ""

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(1.dp)
    ) {
        item { DadosAtuais(currentTemperature, currentTemperatureUnit) }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { DetalhesClima(weatherResponse) }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { PrevisoesHora(weatherResponse) }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaInformacoesPrev() {
    ClimaFacilTheme {
        Surface {
            TelaInformacoes(Weather())
        }
    }
}