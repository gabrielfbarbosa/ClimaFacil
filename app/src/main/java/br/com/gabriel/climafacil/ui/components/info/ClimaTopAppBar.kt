package br.com.gabriel.climafacil.ui.components.info


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gabriel.climafacil.viewmodel.Cidade

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimaTopAppBar(cidade: String, onSearchClicked: () -> Unit) {

    TopAppBar(
        title = { Text(text = cidade) },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),

        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(Icons.Filled.Search, contentDescription = "Pesquisar", tint = Color.Black)

            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    cidades: List<Cidade>

) {
    Column {
        OutlinedTextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { onTextChange(it) },
            textStyle = MaterialTheme.typography.titleLarge,

            placeholder = {
                Text(
                    text = "Buscar cidade",
                    color = Color.Black
                )
            },

            singleLine = true,

            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },

            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp)
        ) {
            items(cidades) { cidade ->
                Text(
                    text = cidade.nome,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClimaTopBarPrev() {
    ClimaTopAppBar("ILHA", { })
}

@Preview(showBackground = true)
@Composable
private fun SearchAppBarPrev() {
    SearchAppBar(
        text = "Buscar cidade",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {},
        listOf(
            Cidade(
                nome = " Cidade 1",
                longitude = 1.0,
                latitude = 3.0
            )
        )
    )
}

