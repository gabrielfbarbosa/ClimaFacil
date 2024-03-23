package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.model.sample.sampleDados
import br.com.gabriel.climafacil.ui.components.info.DadosAtuais
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme

@Composable
fun TelaInformacoes() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(1.dp)
    ) {
        item { DadosAtuais("31°", "Parcial. nublado") }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { DetalhesClima() }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item { PrevisoesHora(sampleDados) }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaInformacoesPrev() {
    ClimaFacilTheme {
        Surface {
            TelaInformacoes()
        }
    }
}