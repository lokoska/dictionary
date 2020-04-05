package lib

/**
 * Result2 just wrap kotlin.Result, because kotlin.Result is currently available only for internal usage
 */
inline class Result2<T>(private val result: Result<T>) {

    fun getOrNull() = result.getOrNull()
    fun onSuccess(action: (T) -> Unit) = Result2(result.onSuccess(action))
    fun onFailure(action: (Throwable) -> Unit) = Result2(result.onFailure(action))

    fun <R> map(transform: (value: T) -> R): Result2<R> = Result2(
        result.map(transform)
    )

    companion object {
        fun <T> success(value: T) = Result2<T>(Result.success(value))
        fun <T> failure(throwable: Throwable) = Result2<T>(Result.failure(throwable))
    }
}
