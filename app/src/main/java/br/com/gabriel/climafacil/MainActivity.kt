package br.com.gabriel.climafacil

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.gabriel.climafacil.apiCORRETA.modelCORRETA.Weather
import br.com.gabriel.climafacil.ui.components.TelaInicial
import br.com.gabriel.climafacil.ui.components.info.ClimaTopAppBar
import br.com.gabriel.climafacil.ui.theme.ClimaFacilTheme
import br.com.gabriel.climafacil.view.WeatherSection
import br.com.gabriel.climafacil.viewmodel.MainViewModel
import br.com.gabriel.climafacil.viewmodel.STATE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.coroutineScope
import java.util.Locale
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {

    //TODO: DEU BOA LOCALIZACAO MELHOR ESTRUTURADO
    private val permission = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var locatioRequired: Boolean = false
    private lateinit var geocoder: Geocoder
    private lateinit var mainViewModel: MainViewModel
    private var city: String = ""

    override fun onResume() {
        super.onResume()
        if (locatioRequired) startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        locationCallback.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationCallback.let {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 100
            )
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(3000)
                .setMaxUpdateDelayMillis(100)
                .build()

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        geocoder = Geocoder(this, Locale.getDefault())

        initViewModel()

        setContent {
            var currentLocation by remember {
                mutableStateOf(LatLng(0.toDouble(), 0.toDouble()))
            }

            locationCallback = object : LocationCallback() {

                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)

                    for (location in p0.locations) {
                        currentLocation = LatLng(location.latitude, location.longitude)

                        val addressList = geocoder.getFromLocation(
                            currentLocation.latitude,
                            currentLocation.longitude,
                            1
                        )

                        city = addressList?.get(0)?.subAdminArea ?: "Cidade não identificada."
                    }
                }
            }

            fetchWeatherInformation(mainViewModel, currentLocation)

            ClimaFacilTheme {
                Surface {
                    LocatioScreen(this@MainActivity, currentLocation, city = city)
                }
            }
        }
    }

    private fun fetchWeatherInformation(mainViewModel: MainViewModel, currentLocation: LatLng) {
        mainViewModel.state = STATE.LOADINMG
        mainViewModel.getPrevisao(currentLocation)
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    @Composable
    private fun LocatioScreen(context: Context, currentLocation: LatLng, city: String) {

        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissionMaps ->
            val temPermissao = permissionMaps.values.reduce { accepted, next -> accepted && next }
            if (temPermissao) {
                locatioRequired = true
                startLocationUpdates()
                Toast.makeText(context, "Acesso a Localizacao Permitida", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, "Acesso a Localizacao NEGADA", Toast.LENGTH_SHORT).show()
            }
        }

        if (permission.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocationUpdates()
        } else {
            launcherMultiplePermissions.launch(permission)
        }

        when (mainViewModel.state) {
            STATE.LOADINMG -> {
                LoadingSection()
            }

            STATE.FAILED -> {
                ErrorSection(mainViewModel.errorMessage)
            }

            else -> {
                TelaInicial(mainViewModel.previsaoResponse, city)
            }
        }
    }

    private @Composable
    fun ErrorSection(errorMessage: String) {
        return Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.i("MainActivity", "ErrorSection: $errorMessage")
            Text(text = errorMessage, color = Color.Red)
        }
    }

    private @Composable
    fun LoadingSection() {
        return Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    }

}

//@Composable
//fun App() {
//    ClimaFacilTheme {
//        Surface {
//            TelaInicial()
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ClimaFacilTheme {
//        App()
//    }
//}