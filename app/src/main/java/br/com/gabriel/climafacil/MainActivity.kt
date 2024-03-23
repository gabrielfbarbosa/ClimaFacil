package br.com.gabriel.climafacil

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import br.com.gabriel.climafacil.api.ApiModule
import br.com.gabriel.climafacil.ui.components.TelaInicial
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                val previsao = ApiModule.climaService.getPrevisao()

                val temperatura = previsao.hourly.temperatures.firstOrNull()
                val temperaturaUnidade = previsao.hourly_units.temperature2m

                val hr = previsao.hourly.time.firstOrNull()

                val umidade = previsao.hourly.relative_humidity_2m.firstOrNull() //Lista
                val umidadeUnidade = previsao.hourly_units.relativeHumidity2m

                val indiceUv = previsao.daily.uvIndexMax.firstOrNull() //Lista
                val indiceUvUnidae = previsao.daily_units.relativeHumidity2m


                val probPrecip = previsao.hourly_units.precipitation_probability

                val sunrise = previsao.daily.sunrise.firstOrNull()
                val sunset = previsao.daily.sunset.firstOrNull()

                val diaNoite = if (previsao.current.is_day == 1) "dia" else "noite"



                Log.d("MainActivity", "Temperatura: $temperatura")

                setContent {
                    val temperatura = temperatura ?: 0.0
                    ClimaFacilTheme {
                        Surface {
                            Column (modifier = Modifier.padding(8.dp)){
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Temperatura TESTE: $temperatura $temperaturaUnidade" )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = " Humidade: $umidade $umidadeUnidade" )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = " Indice UV TESTE: $indiceUv Hs" )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Proibabilidade de Chuvaa TESTE: $probPrecip Hs" )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Nascer Sol TESTE: $sunrise Hs" )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Por Sol TESTE: $sunset Hs" )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "Dia ou Noite: : $diaNoite " )

                            }
//                            App()

                        }
                    }
                }

            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao obter previsão do tempo", e)
            }
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