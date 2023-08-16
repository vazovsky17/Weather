package app.vazovsky.weather.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import kotlinx.parcelize.Parcelize

/**
 * Данные о погоде
 *
 * @param location Локация
 * @param forecast Прогноз
 */
@Parcelize
class ForecastResponse(
    @SerializedName("location") val location: Location?,
    @SerializedName("current") val current: Current?,
    @SerializedName("forecast") val forecast: Forecast?,
) : Parcelable {

    /**
     * Локация для прогноза
     *
     * @param name Название города
     * @param country Страна
     * @param localTime Текущее время в локации
     */
    @Parcelize
    class Location(
        @SerializedName("name") val name: String?,
        @SerializedName("country") val country: String?,
        @SerializedName("localtime") val localTime: String?,
    ) : Parcelable

    /**
     * Текущее состояние погоды
     *
     * @param temp Температура
     * @param condition Погодные условия
     */
    @Parcelize
    class Current(
        @SerializedName("temp_c") val temp: Float?,
        @SerializedName("condition") val condition: Condition?,
    ) : Parcelable

    /**
     * Прогноз
     *
     * @param forecastday Прогноз на несколько дней
     */
    @Parcelize
    class Forecast(
        @SerializedName("forecastday") val forecastday: List<ForecastDay>?,
    ) : Parcelable {

        /**
         * Данные по дате
         *
         * @param date Дата
         * @param day Подробные данные о погоде
         */
        @Parcelize
        class ForecastDay(
            @SerializedName("date") val date: String?,
            @SerializedName("day") val day: Day?,
        ) : Parcelable {

            /**
             * Данные на день
             *
             * @param condition Погодные условия
             * @param avgTemp Средняя температура
             * @param maxWind Максимальная скорость ветра
             * @param avgHumidity Средняя влажность воздуха
             */
            @Parcelize
            class Day(
                @SerializedName("condition") val condition: Condition?,
                @SerializedName("avgtemp_c") val avgTemp: Float?,
                @SerializedName("maxwind_kph") val maxWind: Float?,
                @SerializedName("avghumidity") val avgHumidity: Float?,
            ) : Parcelable
        }
    }

    /**
     * Погодные условия
     *
     * @param text Текстовое описание
     * @param iconUrl Url для графического отображения
     */
    @Parcelize
    class Condition(
        @SerializedName("text") val text: String?,
        @SerializedName("icon") val iconUrl: String?,
    ) : Parcelable
}