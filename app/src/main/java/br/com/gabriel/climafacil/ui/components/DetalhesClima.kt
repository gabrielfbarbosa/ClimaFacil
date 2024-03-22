package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.ui.components.info.CardInformacao
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme

@Composable
fun DetalhesClima() {
    Column()
    {
        Row {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Umidade", "59%")
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Índice UV", "40%")
            }
        }

        Box{
            CardInformacao("Prob. de Precipitacao", "70%")
        }

        Row {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Nascer do Sol", "06:23 hs")
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                CardInformacao("Ponhar do sol", "06:23 hs")
            }
        }

        Box{
            CardInformacao("Dia ou noite ?", "Noite")
        }
    }
}

@Preview
@Composable
private fun DetalhesClimaPrev() {
    ClimaFacilTheme {
        Surface {
            DetalhesClima()
        }
    }
}