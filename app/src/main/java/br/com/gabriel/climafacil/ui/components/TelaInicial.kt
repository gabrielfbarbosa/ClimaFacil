package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.gabriel.climafacil.ui.theme.Amanhecer01
import br.com.gabriel.climafacil.ui.theme.Amanhecer02
import br.com.gabriel.climafacil.ui.theme.Amanhecer03
import br.com.gabriel.climafacil.ui.theme.Amanhecer04
import br.com.gabriel.climafacil.ui.theme.Amanhecer05
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import br.com.gabriel.climafacil.ui.theme.Noite01
import br.com.gabriel.climafacil.ui.theme.Noite02
import br.com.gabriel.climafacil.ui.theme.Noite03
import br.com.gabriel.climafacil.ui.theme.Noite04
import br.com.gabriel.climafacil.ui.theme.Noite05
import java.util.Calendar

val DIA = listOf(
    Amanhecer01,
    Amanhecer02,
    Amanhecer03,
    Amanhecer04,
    Amanhecer05
)
val NOITE = listOf(
    Noite03,
    Noite04,
    Noite05,
)


fun getBackgroundColor(): List<Color> {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return if (currentHour in 6..18) DIA else NOITE
}

@Composable
fun TelaInicial() {
    val backgroundColor = getBackgroundColor()

    Scaffold(modifier = Modifier
        .fillMaxSize(),

        topBar = {
            ClimaTopAppBar("Aquii")
        },

    ) {

        Column(
            Modifier
                .padding(it)
                .background(
                    Brush.verticalGradient(
                        colors = backgroundColor
                    )
                )

        ) {
            TelaInformacoes()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CorFundoPreview() {
    ClimaFacilTheme {
        Surface {
            TelaInicial()
        }
    }
}
