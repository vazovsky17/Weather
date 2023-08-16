package app.vazovsky.weather.data.repository

import app.vazovsky.weather.data.model.ForecastWeather

interface WeatherRepository {

    /**
     * Получение данных о погоде
     * @param city Город
     * @param days Количество крайних дней, за которые нужна информация о погоде
     */
    suspend fun getWeather(city: String, days: Int): ForecastWeather
}