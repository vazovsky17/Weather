package app.vazovsky.weather.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.data.remote.base.LoadableResult
import app.vazovsky.weather.domain.usecase.GetNearestWeatherUseCase
import app.vazovsky.weather.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNearestWeatherUseCase: GetNearestWeatherUseCase,
) : BaseViewModel() {

    /** Данные о погоде */
    private val _forecastWeatherLiveData = MutableLiveData<LoadableResult<ForecastWeather>>()
    val forecastWeatherLiveData: LiveData<LoadableResult<ForecastWeather>> = _forecastWeatherLiveData

    fun getNearestWeather(city: String) {
        _forecastWeatherLiveData.launchLoadData(
            getNearestWeatherUseCase.executeFlow(GetNearestWeatherUseCase.Params(city))
        )
    }
}