package br.com.gabriel.climafacil.redux.reducers

import PrevisaoResponseSEGUNDA
import br.com.gabriel.climafacil.redux.states.WeatherState
import com.github.oxeanbits.redukt.actions.Action
import com.github.oxeanbits.redukt.reducers.Reducer

class WeatherReducer : Reducer<WeatherState> {
    override fun reduce(state: WeatherState, action: Action<*>): WeatherState {
        return when (action.name) {
            "START_LOADING" -> state.copy(loading = true)
            "FETCH_SUCCESS" -> state.copy(previsao = action.payload as PrevisaoResponseSEGUNDA, loading = false)
            "FETCH_ERROR" -> state.copy(error = action.payload as String, loading = false)
            else -> state
        }
    }
}