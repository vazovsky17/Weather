package app.vazovsky.weather.managers

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/** Пример: 17 march */
private const val DATE_TEMPLATE = "dd MMMM"

/** Пример: 2023-08-16 */
private const val DATE_FULL_TEMPLATE = "yyyy-MM-dd"

private const val UNDEFINED_DATE = "Undefined"

class DateFormatter @Inject constructor() {

    private val defaultLocale: Locale = Locale("en")

    private val dateSimpleFormat = SimpleDateFormat(DATE_TEMPLATE, defaultLocale)
    private val dateFullSimpleFormat = SimpleDateFormat(DATE_FULL_TEMPLATE, defaultLocale)

    fun convertDate(date: String?): String {
        var formattedDate = date
        try {
            dateFullSimpleFormat.timeZone = TimeZone.getTimeZone("UTC")
            val value = formattedDate?.let { dateFullSimpleFormat.parse(it) }
            dateSimpleFormat.timeZone = TimeZone.getDefault()
            formattedDate = value?.let { dateSimpleFormat.format(it) } ?: UNDEFINED_DATE
        } catch (e: Exception) {
            formattedDate = UNDEFINED_DATE
        }
        return formattedDate ?: UNDEFINED_DATE
    }
}
