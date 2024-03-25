package br.com.gabriel.climafacil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class CidadeViewModel : ViewModel() {


    private val _searchWidgetState: MutableStateFlow<SearchWidgetState> =
        MutableStateFlow(value = SearchWidgetState.CLOSED)
    val searchWidgetState: StateFlow<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableStateFlow<String> =
        MutableStateFlow(value = "")
    val searchTextState: StateFlow<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    private val _cidades = MutableStateFlow(todasCidades)
    val cidades = searchTextState
        .debounce(1000L)
        .combine(_cidades) { text, cidades ->
            if (text.isBlank()) {
                cidades
            } else {
                cidades.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _cidades.value
        )
}

data class Cidade(
    val nome: String,
    val latitude: Double,
    val longitude: Double
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchinCombinations = listOf(
            "$nome",
            "${nome.first()}",
        )
        return matchinCombinations.any {
            it.contains(query, ignoreCase = true)
        }


    }
}

private val todasCidades = listOf(
    Cidade(
        nome = "Sao Paulo",
        latitude = -23.5506507,
        longitude = -46.6333824
    ),
    Cidade(
        nome = "Rio de Janeiro",
        latitude = -22.9110137,
        longitude = -43.2093727
    ),
    Cidade(
        nome = "Manaus",
        latitude = -3.1316333,
        longitude = -59.9825041
    )
)