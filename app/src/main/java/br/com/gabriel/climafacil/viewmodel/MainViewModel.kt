package br.com.gabriel.climafacil.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.climafacil.api.RetrofitClient
import br.com.gabriel.climafacil.api.model.Clima
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

enum class STATE{
    LOADINMG,
    SUCCESS,
    FAILED
}

class MainViewModel: ViewModel() {
    var state by mutableStateOf(STATE.LOADINMG)

    var previsaoResponse: Clima by mutableStateOf(Clima())

    var errorMessage: String by mutableStateOf("")

    fun getPrevisao(latLng: LatLng) {
        viewModelScope.launch {
            state = STATE.LOADINMG
            val apiService = RetrofitClient.getInstance()
            try {
                val apiResponse = apiService.getPrevisao(latLng.latitude, latLng.longitude)
                previsaoResponse = apiResponse
                state = STATE.SUCCESS
            } catch (e: Exception){
                errorMessage = e.message.toString()
                state = STATE.FAILED
            }
        }
    }
}