package br.com.gabriel.climafacil.redux.actions

import PrevisaoResponseSEGUNDA

sealed class WeatherAction {
    object StartLoading : WeatherAction()
    data class FetchSuccess(val previsao: PrevisaoResponseSEGUNDA) : WeatherAction()
    data class FetchError(val error: String) : WeatherAction()
}