package app.vazovsky.weather.data.remote.base

interface Progressable<T> {
    val isInProgress: Boolean

    fun updateProgress(inProgress: Boolean): T
}