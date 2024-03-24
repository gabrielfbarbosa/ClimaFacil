package br.com.gabriel.climafacil.ui.components.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DadosAtuais(temperaturaAtual: Double, unidadeTemp: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(
                text = "$temperaturaAtual $unidadeTemp",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DadosAtuaisPrev() {
    DadosAtuais(18.0, "ºC")
}