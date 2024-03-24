package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.gabriel.climafacil.apiCORRETA.modelCORRETA.Weather
import br.com.gabriel.climafacil.ui.components.info.ClimaTopAppBar
import br.com.gabriel.climafacil.ui.theme.Amanhecer01
import br.com.gabriel.climafacil.ui.theme.Amanhecer02
import br.com.gabriel.climafacil.ui.theme.Amanhecer03
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import br.com.gabriel.climafacil.ui.theme.Noite03
import br.com.gabriel.climafacil.ui.theme.Noite04
import br.com.gabriel.climafacil.ui.theme.Noite05
import br.com.gabriel.climafacil.utils.ClimaUtils
import java.util.Calendar

val DIA = listOf(
    Amanhecer01,
    Amanhecer02,
    Amanhecer03,
)
val NOITE = listOf(
    Noite03,
    Noite04,
    Noite05,
)

@Composable
fun TelaInicial(weatherResponse: Weather, cidade: String) {
    fun getBackgroundColor(): List<Color> {
        val diaNoite = weatherResponse.current?.is_day
        return if (diaNoite == 1) DIA else NOITE
    }
    val backgroundColor = getBackgroundColor()

    Box( modifier = Modifier.background(Brush.verticalGradient(backgroundColor))) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,

            topBar = {
                ClimaTopAppBar(cidade = cidade)
            }
        ) { contentPadding ->
            Column(Modifier.padding(contentPadding)) {
                TelaInformacoes(weatherResponse)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CorFundoPreview() {
    ClimaFacilTheme {
        Surface {
            TelaInicial(Weather(), "Cidade")
        }
    }
}
