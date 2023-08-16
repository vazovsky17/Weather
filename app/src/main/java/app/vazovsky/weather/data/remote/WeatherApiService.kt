package app.vazovsky.weather.data.remote

import app.vazovsky.weather.data.remote.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("key") key: String,
    ): ForecastResponse
}