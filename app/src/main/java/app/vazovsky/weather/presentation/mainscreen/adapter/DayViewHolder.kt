package app.vazovsky.weather.presentation.mainscreen.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.vazovsky.weather.R
import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.databinding.ItemDayBinding
import app.vazovsky.weather.extensions.formatWithoutTrailingZero
import app.vazovsky.weather.extensions.inflate
import app.vazovsky.weather.extensions.load
import app.vazovsky.weather.managers.DateFormatter
import app.vazovsky.weather.managers.UrlFormatter
import by.kirich1409.viewbindingdelegate.viewBinding

class DayViewHolder(
    parent: ViewGroup,
    private val dateFormatter: DateFormatter,
    private val urlFormatter: UrlFormatter,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_day)) {

    private val binding by viewBinding(ItemDayBinding::bind)

    fun bind(item: ForecastWeather.Day) = with(binding) {
        textViewDate.text = dateFormatter.convertDate(item.date)
        textViewDescription.text = item.conditionText
        textViewAvgTemperature.text = bindAvgTemperature(item.avgTemperature)
        textViewMaxWind.text = bindMaxWind(item.maxWind)
        textViewAvgHumidity.text = bindAvgHumidity(item.avgHumidity)
        imageViewIcon.load(item.conditionIconUrl?.let { urlFormatter.addPrefix(it) })
    }

    private fun bindAvgTemperature(temperature: Float) = with(binding.root.resources) {
        return@with buildString {
            append(getString(R.string.weather_avg_temperature_prefix))
            append(" ")
            append(temperature.formatWithoutTrailingZero())
            append(getString(R.string.weather_avg_temperature_postfix))
        }
    }

    private fun bindMaxWind(wind: Float) = with(binding.root.resources) {
        return@with buildString {
            append(getString(R.string.weather_max_wind_prefix))
            append(" ")
            append(wind.formatWithoutTrailingZero())
            append(" ")
            append(getString(R.string.weather_max_wind_postfix))
        }
    }

    private fun bindAvgHumidity(humidity: Float) = with(binding.root.resources) {
        return@with buildString {
            append(getString(R.string.weather_avg_humidity_prefix))
            append(" ")
            append(humidity.formatWithoutTrailingZero())
            append(" ")
            append(getString(R.string.weather_avg_humidity_postfix))
        }
    }
}