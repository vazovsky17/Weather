package app.vazovsky.weather.data.remote

import app.vazovsky.weather.data.remote.response.ForecastResponse

class SemimockWeatherApiService(
    private val apiService: WeatherApiService,
    private val mockApiService: MockWeatherApiService,
) : WeatherApiService {

    override suspend fun getForecastWeather(city: String, days: Int, key: String): ForecastResponse {
        return apiService.getForecastWeather(city, days, key)
    }
}