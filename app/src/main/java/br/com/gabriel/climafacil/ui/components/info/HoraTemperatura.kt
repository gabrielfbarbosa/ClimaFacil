package br.com.gabriel.climafacil.ui.components.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.model.Dado
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme

@Composable
fun HoraTemperatura(dado: Dado) {
    Column(
        modifier = Modifier
            .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = dado.hr,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        )

        Text(
            text = dado.temp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TelaPrincipalPrev() {
    ClimaFacilTheme {
        Surface {
            HoraTemperatura(Dado( "18:00", "20 °C"))
        }
    }
}