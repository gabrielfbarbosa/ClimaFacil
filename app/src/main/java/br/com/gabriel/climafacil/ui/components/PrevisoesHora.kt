package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.model.Response
import br.com.gabriel.climafacil.sample.sampleDados

@Composable
fun PrevisoesHora(dados: List<Response>) {
    Card(elevation = CardDefaults.cardElevation(4.dp)) {
        Column {
            Text(
                text = "Estatus da hora. Minima de XX °C",

                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, bottom = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp
                    ),

            ) {
                items(dados) { d ->
                    HoraTemperatura(dado = d)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PrevisoesHoraPrev() {
    PrevisoesHora(sampleDados)
}