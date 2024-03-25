package br.com.gabriel.climafacil.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import br.com.gabriel.climafacil.api.model.Clima
import br.com.gabriel.climafacil.ui.components.info.ClimaTopAppBar
import br.com.gabriel.climafacil.ui.components.info.SearchAppBar
import br.com.gabriel.climafacil.ui.theme.Amanhecer01
import br.com.gabriel.climafacil.ui.theme.Amanhecer02
import br.com.gabriel.climafacil.ui.theme.Amanhecer03
import br.com.gabriel.climafacil.ui.theme.Noite03
import br.com.gabriel.climafacil.ui.theme.Noite04
import br.com.gabriel.climafacil.ui.theme.Noite05
import br.com.gabriel.climafacil.viewmodel.Cidade
import br.com.gabriel.climafacil.viewmodel.CidadeViewModel
import br.com.gabriel.climafacil.viewmodel.SearchWidgetState

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

@Composable
fun TelaInicial(weatherResponse: Clima, cidade: String, cidadeViewModel: CidadeViewModel) {
    fun getBackgroundColor(): List<Color> {
        val diaNoite = weatherResponse.current?.is_day
        return if (diaNoite == 1) DIA else NOITE
    }
    val backgroundColor = getBackgroundColor()

    val searchWidgetState by cidadeViewModel.searchWidgetState.collectAsState()
    val searchTextState by cidadeViewModel.searchTextState.collectAsState()

    val cidades by cidadeViewModel.cidades.collectAsState()

    Box( modifier = Modifier.background(Brush.verticalGradient(backgroundColor))) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,

            topBar = {

                MainTelaInicial(
                    cidade = cidade,
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    onTextChange = {
                        cidadeViewModel.updateSearchTextState(newValue = it)
                    },
                    onCloseClicked = {
                        cidadeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                    },
                    onSearchClicked = {
                    },
                    onSearchTriggered = {
                        cidadeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                    },
                    cidades
                )
            },

        ){ contentPadding ->
            Column(Modifier.padding(contentPadding)) {
                TelaInformacoes(weatherResponse)
            }
        }
    }
}

@Composable
fun MainTelaInicial( cidade: String,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    cidades: List<Cidade>
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            ClimaTopAppBar( cidade = cidade,
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked,
                cidades
            )
        }
    }
}