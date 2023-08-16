package app.vazovsky.weather.domain.usecase

import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.data.repository.WeatherRepository
import app.vazovsky.weather.domain.base.UseCaseUnary
import javax.inject.Inject
import kotlinx.coroutines.delay

private const val DEFAULT_DAYS_COUNT = 5

class GetNearestWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : UseCaseUnary<GetNearestWeatherUseCase.Params, ForecastWeather>() {

    override suspend fun execute(params: Params): ForecastWeather {
        delay(1000L)
        return weatherRepository.getWeather(params.city, params.days)
    }

    data class Params(
        val city: String,
        val days: Int = DEFAULT_DAYS_COUNT,
    )
}