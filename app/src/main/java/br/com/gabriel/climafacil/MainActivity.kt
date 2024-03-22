package br.com.gabriel.climafacil

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import br.com.gabriel.climafacil.apitres.ApiModuleAPITRES
import br.com.gabriel.climafacil.ui.components.TelaInicial
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                val previsao = ApiModuleAPITRES.climaService.getPrevisao()
                val temperatura = previsao.hourly.temperatures.firstOrNull()
                val hr = previsao.hourly.time.firstOrNull()
                val s = previsao.current.relativeHumidity2m


                Log.d("MainActivity", "Temperatura: $temperatura")

                setContent {
                    val temp = temperatura ?: 0.0
                    ClimaFacilTheme {
                        Surface {
                            Column {
                                Text(text = " TESTE: $temp °C" )
                                Text(text = " TESTE: $hr Hs" )
                                Text(text = " HumidadeTESTE: $s Hs" )

                            }

                        }
                    }
                }

            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao obter previsão do tempo", e)
            }
        }

//        setContent {
//            App()
//        }
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