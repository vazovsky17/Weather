package app.vazovsky.weather.data.remote

import app.vazovsky.weather.data.remote.response.ForecastResponse

class MockWeatherApiService : WeatherApiService {

    override suspend fun getForecastWeather(city: String, days: Int, key: String): ForecastResponse {
        return ForecastResponse(
            location = ForecastResponse.Location(
                name = "New York",
                country = "United States of America",
                localTime = "2022-07-22 16:49",
            ),
            current = ForecastResponse.Current(
                temp = 34.4F,
                condition = ForecastResponse.Condition(
                    text = "Partly cloudy",
                    iconUrl = "cdn.weatherapi.com/weather/64x64/day/116.png",
                )
            ),
            forecast = ForecastResponse.Forecast(
                forecastday = listOf(
                    ForecastResponse.Forecast.ForecastDay(
                        date = "2023-08-16",
                        day = ForecastResponse.Forecast.ForecastDay.Day(
                            condition = ForecastResponse.Condition(
                                text = "Partly cloudy",
                                iconUrl = "cdn.weatherapi.com/weather/64x64/day/116.png",
                            ),
                            avgTemp = 30.7F,
                            maxWind = 20.5F,
                            avgHumidity = 53.0F,
                        )
                    ),
                    ForecastResponse.Forecast.ForecastDay(
                        date = "2023-08-16",
                        day = ForecastResponse.Forecast.ForecastDay.Day(
                            condition = ForecastResponse.Condition(
                                text = "Partly cloudy",
                                iconUrl = "cdn.weatherapi.com/weather/64x64/day/116.png",
                            ),
                            avgTemp = 30.7F,
                            maxWind = 20.5F,
                            avgHumidity = 53.0F,
                        )
                    ),
                    ForecastResponse.Forecast.ForecastDay(
                        date = "2023-08-16",
                        day = ForecastResponse.Forecast.ForecastDay.Day(
                            condition = ForecastResponse.Condition(
                                text = "Partly cloudy",
                                iconUrl = "cdn.weatherapi.com/weather/64x64/day/116.png",
                            ),
                            avgTemp = 30.7F,
                            maxWind = 20.5F,
                            avgHumidity = 53.0F,
                        )
                    ),
                    ForecastResponse.Forecast.ForecastDay(
                        date = "2023-08-16",
                        day = ForecastResponse.Forecast.ForecastDay.Day(
                            condition = ForecastResponse.Condition(
                                text = "Partly cloudy",
                                iconUrl = "cdn.weatherapi.com/weather/64x64/day/116.png",
                            ),
                            avgTemp = 30.7F,
                            maxWind = 20.5F,
                            avgHumidity = 53.0F,
                        )
                    ),
                    ForecastResponse.Forecast.ForecastDay(
                        date = "2023-08-16",
                        day = ForecastResponse.Forecast.ForecastDay.Day(
                            condition = ForecastResponse.Condition(
                                text = "Partly cloudy",
                                iconUrl = "cdn.weatherapi.com/weather/64x64/day/116.png",
                            ),
                            avgTemp = 30.7F,
                            maxWind = 20.5F,
                            avgHumidity = 53.0F,
                        )
                    )
                )
            )
        )
    }
}