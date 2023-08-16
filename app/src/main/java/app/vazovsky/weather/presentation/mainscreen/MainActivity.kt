package app.vazovsky.weather.presentation.mainscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import app.vazovsky.weather.R
import app.vazovsky.weather.data.model.ForecastWeather
import app.vazovsky.weather.databinding.ActivityMainBinding
import app.vazovsky.weather.extensions.addLinearSpaceItemDecoration
import app.vazovsky.weather.extensions.fitTopInsetsWithPadding
import app.vazovsky.weather.extensions.formatWithoutTrailingZero
import app.vazovsky.weather.extensions.load
import app.vazovsky.weather.managers.DateFormatter
import app.vazovsky.weather.managers.UrlFormatter
import app.vazovsky.weather.presentation.base.BaseActivity
import app.vazovsky.weather.presentation.mainscreen.adapter.DaysAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    @Inject lateinit var daysAdapter: DaysAdapter
    @Inject lateinit var dateFormatter: DateFormatter
    @Inject lateinit var urlFormatter: UrlFormatter

    override fun callOperations() {
        viewModel.getNearestWeather(getString(R.string.weather_city))
    }

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        root.fitTopInsetsWithPadding()

        stateViewFlipper.setRetryMethod { viewModel.getNearestWeather(getString(R.string.weather_city)) }
        emptyViewDays.setButtonTextAndClickListener(R.string.empty_update) { viewModel.getNearestWeather(getString(R.string.weather_city)) }
        setupRecyclerView()
    }

    override fun onBindViewModel() = with(viewModel) {
        forecastWeatherLiveData.observe { result ->
            binding.stateViewFlipper.setStateFromResult(result)
            setupVisibility(result.isSuccess)
            result.doOnSuccess { weather ->
                bindWeather(weather)
            }
            result.doOnFailure {
                Timber.d(it.message)
            }
        }
    }

    private fun bindWeather(weather: ForecastWeather) = with(binding) {
        textViewCity.text = bindCity(weather)
        textViewDate.text = dateFormatter.convertDate(weather.localTime)
        textViewDescription.text = weather.current.conditionText
        textViewTemperature.text = bindCurrentTemperature(weather.current.temperature)

        imageViewIcon.load(weather.current.conditionIconUrl?.let { urlFormatter.addPrefix(it) })

        daysAdapter.submitList(weather.days)
    }

    private fun bindCity(weather: ForecastWeather) = buildString {
        append(weather.city)
        append(", ")
        append(weather.country)
    }

    private fun bindCurrentTemperature(temperature: Float) = buildString {
        append(getString(R.string.weather_current_temperature_prefix))
        append(" ")
        append(temperature.formatWithoutTrailingZero())
        append(getString(R.string.weather_current_temperature_postfix))
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerViewDays.adapter = daysAdapter
        recyclerViewDays.emptyView = emptyViewDays
        recyclerViewDays.addLinearSpaceItemDecoration(
            spacing = R.dimen.margin_8,
            showFirstHorizontalDivider = true,
            showLastHorizontalDivider = true,
        )
    }

    private fun setupVisibility(isVisible: Boolean) = with(binding) {
        textViewDate.isVisible = isVisible
        imageViewIcon.isVisible = isVisible
        textViewCity.isVisible = isVisible
        textViewDescription.isVisible = isVisible
        textViewTemperature.isVisible = isVisible
        emptyViewDays.isVisible = isVisible
    }
}