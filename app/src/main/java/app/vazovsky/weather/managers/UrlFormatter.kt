package app.vazovsky.weather.managers

import javax.inject.Inject

private const val DEFAULT_PREFIX = "https:"

class UrlFormatter @Inject constructor() {
    fun addPrefix(url: String): String {
        return if (url.startsWith(DEFAULT_PREFIX, ignoreCase = true)) {
            url
        } else buildString {
            append(DEFAULT_PREFIX)
            append(url)
        }
    }
}