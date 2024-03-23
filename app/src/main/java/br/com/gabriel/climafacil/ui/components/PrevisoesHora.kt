package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.model.Dado
import br.com.gabriel.climafacil.model.sample.sampleDados
import br.com.gabriel.climafacil.ui.components.info.HoraTemperatura

@Composable
fun PrevisoesHora(dados: List<Dado>) {

    OutlinedCard(modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        shape = MaterialTheme.shapes.small,
        ) {

        Column(Modifier.padding(8.dp)) {

            Text(
                text = "Estatus da hora. Minima de XX °C",

                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )

            LazyRow( modifier = Modifier.fillMaxWidth()) {
                items(dados) { d ->
                    HoraTemperatura(dado = d)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevisoesHoraPrev() {
    PrevisoesHora(sampleDados)
}