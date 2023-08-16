package app.vazovsky.weather.data.mapper

import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.data.remote.response.ForecastResponse
import app.vazovsky.weather.extensions.orDefault
import javax.inject.Inject

class ForecastWeatherMapper @Inject constructor(

) {

    fun fromApiToModel(apiModel: ForecastResponse?): ForecastWeather {
        return ForecastWeather(
            city = apiModel?.location?.name.orDefault(),
            country = apiModel?.location?.country.orDefault(),
            localTime = apiModel?.location?.localTime.orDefault(),
            current = fromApiToModel(apiModel?.current),
            days = apiModel?.forecast?.forecastday.orEmpty().map { fromApiToModel(it) },
        )
    }

    private fun fromApiToModel(apiModel: ForecastResponse.Current?): ForecastWeather.Current {
        return ForecastWeather.Current(
            temperature = apiModel?.temp.orDefault(),
            conditionText = apiModel?.condition?.text.orDefault(),
            conditionIconUrl = apiModel?.condition?.iconUrl,
        )
    }

    private fun fromApiToModel(apiModel: ForecastResponse.Forecast.ForecastDay?): ForecastWeather.Day {
        return ForecastWeather.Day(
            date = apiModel?.date,
            conditionText = apiModel?.day?.condition?.text.orDefault(),
            conditionIconUrl = apiModel?.day?.condition?.iconUrl,
            avgTemperature = apiModel?.day?.avgTemp.orDefault(),
            maxWind = apiModel?.day?.maxWind.orDefault(),
            avgHumidity = apiModel?.day?.avgHumidity.orDefault(),
        )
    }
}