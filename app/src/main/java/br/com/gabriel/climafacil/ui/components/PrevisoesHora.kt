package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.api.model.Clima
import br.com.gabriel.climafacil.modelinformacoes.Dado
import br.com.gabriel.climafacil.ui.components.info.HoraTemperatura
import br.com.gabriel.climafacil.utils.ClimaUtils

@Composable
fun PrevisoesHora(weather: Clima) {

    val times = weather.hourly?.time ?: emptyList()
    val temperatures = weather.hourly?.temperatures ?: emptyList()
    val tempUnity = weather.hourly_units?.temperature2m ?: ""

    val dadosPorHora = List(times.size) { index ->
        Dado(
            hr = ClimaUtils().formatarHora(times[index]),
            temp = temperatures.getOrNull(index) ?: 0.0,
            tempUnit = tempUnity

        )
    }

    OutlinedCard(modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = MaterialTheme.shapes.small,
        ) {

        Column(Modifier.padding(8.dp)) {

            Text(
                text = "Temperatura por hora",

                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )

            LazyRow( modifier = Modifier.fillMaxWidth()) {
                items(dadosPorHora) { d ->
                    HoraTemperatura(dado = d)
                }
            }
        }
    }
}