package br.com.gabriel.climafacil.utils

import br.com.gabriel.climafacil.api.model.Clima
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClimaUtils {
    fun obterIndiceDadosAtuais(weather: Clima): Int? {
        val dataHoraAtual = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault()).format(Date())

        val partes = dataHoraAtual.split("T")
        val dataHoraAtualFormatada = partes[0] + " " + partes[1]

        weather.hourly?.time?.forEachIndexed { index, horaApi ->
            val partesHoraApi = horaApi.split("T")
            val horaApiFormatada = partesHoraApi[0] + " " + partesHoraApi[1]

            if (dataHoraAtualFormatada <= horaApiFormatada) {
                return index
            }
        }

        return null
    }

    fun obterIndiceDadosDiarios(weather: Clima): Int? {
        val dataAtual = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        return weather.daily?.time?.indexOfFirst { it.startsWith(dataAtual) }
    }


    fun formatarHora(dataHora: String): String {
        val formatoOriginal = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val formatoDesejado = SimpleDateFormat("HH:mm", Locale.getDefault())

        val data: Date = formatoOriginal.parse(dataHora) ?: Date()

        return formatoDesejado.format(data)
    }
}
