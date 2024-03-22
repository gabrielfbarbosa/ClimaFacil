package br.com.gabriel.climafacil.redux.states

import PrevisaoResponseSEGUNDA

data class WeatherState(
    val previsao: PrevisaoResponseSEGUNDA? = null,
    val loading: Boolean = false,
    val error: String? = null
)