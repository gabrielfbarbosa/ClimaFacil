package br.com.gabriel.climafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.gabriel.climafacil.ui.components.TelaInicial
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    ClimaFacilTheme {
        Surface {
            TelaInicial()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClimaFacilTheme {
        App()
    }
}