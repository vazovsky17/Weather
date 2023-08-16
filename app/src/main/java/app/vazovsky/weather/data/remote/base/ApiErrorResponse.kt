package app.vazovsky.weather.data.remote.base

import com.google.gson.annotations.SerializedName

/**
 * Данные об ошибке запроса
 * @param error Ошибка
 */
data class ApiErrorResponse(
    @SerializedName("error") val error: Data?,
) {
    /**
     * Ошибка
     * @param code Код ошибки
     * @param message Сообщение об ошибке
     */
    data class Data(
        @SerializedName("code") val code: String?,
        @SerializedName("message") val message: String?,
    )
}