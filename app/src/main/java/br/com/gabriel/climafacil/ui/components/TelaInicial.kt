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
import br.com.gabriel.climafacil.ui.theme.Amanhecer01
import br.com.gabriel.climafacil.ui.theme.Amanhecer02
import br.com.gabriel.climafacil.ui.theme.Amanhecer03
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import br.com.gabriel.climafacil.ui.theme.Noite03
import br.com.gabriel.climafacil.ui.theme.Noite04
import br.com.gabriel.climafacil.ui.theme.Noite05
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


fun getBackgroundColor(): List<Color> {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return if (currentHour in 6..18) DIA else NOITE
}

@Composable
fun TelaInicial() {
    val backgroundColor = getBackgroundColor()

    Box(modifier = Modifier.background(Brush.verticalGradient(DIA))
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                ClimaTopAppBar("Aquii")
            },

            ) {

            Column(
                Modifier
                    .padding(it)

            ) {
                TelaInformacoes()
            }
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
