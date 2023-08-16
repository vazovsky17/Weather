package app.vazovsky.weather.extensions

fun Float?.orDefault(default: Float = .0f): Float = this ?: default

fun String?.orDefault(default: String = ""): String = this ?: default
