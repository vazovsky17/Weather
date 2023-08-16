package app.vazovsky.weather.extensions

/**
 * Форматирование дробных чисел до десятых и без нуля на конце.
 */
fun Float.formatWithoutTrailingZero(): String {
    val temp = "%.1f".format(this)
    if (temp.last() == '0') {
        return temp.substring(0, temp.length - 2)
    }
    return temp.replace(',', '.')
}
