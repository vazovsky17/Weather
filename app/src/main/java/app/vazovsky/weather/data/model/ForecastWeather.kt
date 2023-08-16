package app.vazovsky.weather.data.model

import android.os.Parcelable
import java.time.LocalDate
import kotlinx.parcelize.Parcelize

/**
 * Данные о погоде
 *
 * @param city Город
 * @param country Страна
 * @param localTime Текущее время в локации
 * @param days Прогноз на несколько дней
 */
@Parcelize
data class ForecastWeather(
    val city: String,
    val country: String,
    val localTime: String,
    val current: Current,
    val days: List<Day>,
) : Parcelable {

    /**
     * Текущее состояние погоды
     *
     * @param temperature Текущая температура
     * @param conditionText Текстовое описание текущих погодных условий
     * @param conditionIconUrl Url для графического отображения текущих погодных условий
     */
    @Parcelize
    data class Current(
        val temperature: Float,
        val conditionText: String,
        val conditionIconUrl: String?,
    ) : Parcelable

    /**
     * Данные на день
     *
     * @param date Дата
     * @param conditionText Текстовое описание погодных условий
     * @param conditionIconUrl Url для графического отображения погодных условий
     * @param avgTemperature Средняя температура
     * @param maxWind Максимальная скорость ветра
     * @param avgHumidity Средняя влажность воздуха
     */
    @Parcelize
    data class Day(
        val date: String?,
        val conditionText: String,
        val conditionIconUrl: String?,
        val avgTemperature: Float,
        val maxWind: Float,
        val avgHumidity: Float,
    ) : Parcelable
}
