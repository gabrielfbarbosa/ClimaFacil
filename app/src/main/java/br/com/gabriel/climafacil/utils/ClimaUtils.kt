package br.com.gabriel.climafacil.utils

import br.com.gabriel.climafacil.apiCORRETA.modelCORRETA.Weather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ClimaUtils {
    fun obterIndiceDadosAtuais(weather: Weather): Int? {
        // Obtenha a data e hora atual do sistema
        val dataHoraAtual = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault()).format(Date())

        // Divida a data e a hora atual
        val partes = dataHoraAtual.split("T")
        val dataHoraAtualFormatada = partes[0] + " " + partes[1]

        // Verifique cada item na lista de tempo hourly.time
        weather.hourly?.time?.forEachIndexed { index, horaApi ->
            // Converta a hora da API para o mesmo formato da hora atual
            val partesHoraApi = horaApi.split("T")
            val horaApiFormatada = partesHoraApi[0] + " " + partesHoraApi[1]

            // Compare a hora atual com a hora da API
            if (dataHoraAtualFormatada <= horaApiFormatada) {
                // Se a hora atual for menor ou igual à hora da API, retorne o índice atual
                return index
            }
        }

        // Se a hora atual for maior do que todas as horas da lista, retorne nulo
        return null
    }

    fun obterIndiceDadosDiarios(weather: Weather): Int? {
        // Obter a data atual do sistema no formato 'yyyy-MM-dd'
        val dataAtual = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Procurar o índice correspondente na lista de datas diárias
        return weather.daily?.time?.indexOfFirst { it.startsWith(dataAtual) }
    }


    fun formatarHora(dataHora: String): String {
        val formatoOriginal = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val formatoDesejado = SimpleDateFormat("HH:mm", Locale.getDefault())

        val data: Date = formatoOriginal.parse(dataHora) ?: Date()

        return formatoDesejado.format(data)
    }
}
