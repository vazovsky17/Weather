package app.vazovsky.weather.data.repository

import app.vazovsky.weather.data.mapper.ForecastWeatherMapper
import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.data.remote.WeatherApiService
import javax.inject.Inject

private const val ACCESS_KEY = "5495cd605ff14cdb965184447231408"

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val forecastWeatherMapper: ForecastWeatherMapper,
) : WeatherRepository {

    override suspend fun getWeather(city: String, days: Int): ForecastWeather {
        return forecastWeatherMapper.fromApiToModel(apiService.getForecastWeather(city, days, ACCESS_KEY))
    }
}