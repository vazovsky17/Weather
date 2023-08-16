package app.vazovsky.weather.di

import app.vazovsky.weather.data.repository.WeatherRepository
import app.vazovsky.weather.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(
        genresRepository: WeatherRepositoryImpl
    ): WeatherRepository
}