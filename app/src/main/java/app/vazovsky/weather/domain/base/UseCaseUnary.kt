package app.vazovsky.weather.domain.base

import app.vazovsky.weather.data.remote.base.LoadableResult
import app.vazovsky.weather.data.remote.base.Progressable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** Данные для юз-кейсов */
abstract class UseCaseUnary<in Params, Result> : UseCase where Result : Any {

    abstract suspend fun execute(params: Params): Result

    fun executeFlow(params: Params, payload: Result? = null): Flow<LoadableResult<Result>> {
        return loadData(payload) { execute(params) }
    }

    /** Загрузка данных */
    private fun loadData(
        payload: Result? = null,
        block: suspend () -> Result,
    ): Flow<LoadableResult<Result>> = flow {
        try {
            var updatedPayload = updateProgress(payload, true)
            emit(LoadableResult.loading(updatedPayload))
            val result = block()
            updatedPayload = updateProgress(payload, false)
            emit(LoadableResult.success(result, updatedPayload))
        } catch (error: Throwable) {
            val updatedPayload = updateProgress(payload, false)
            emit(LoadableResult.failure(error, updatedPayload))
        }
    }

    /** Обновление прогресса загрузки данных */
    @Suppress("UNCHECKED_CAST")
    private fun updateProgress(payload: Result?, inProgress: Boolean): Result? {
        return if (payload != null && payload is Progressable<*>) {
            payload.updateProgress(inProgress) as Result
        } else {
            payload
        }
    }
}