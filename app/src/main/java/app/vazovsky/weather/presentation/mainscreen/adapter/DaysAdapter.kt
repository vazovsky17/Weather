package app.vazovsky.weather.presentation.mainscreen.adapter

import android.view.ViewGroup
import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.managers.DateFormatter
import app.vazovsky.weather.managers.UrlFormatter
import app.vazovsky.weather.presentation.views.recyclerview.BaseAdapter
import javax.inject.Inject

class DaysAdapter @Inject constructor(
    private val dateFormatter: DateFormatter,
    private val urlFormatter: UrlFormatter,
) : BaseAdapter<ForecastWeather.Day, DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(parent, dateFormatter, urlFormatter)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}