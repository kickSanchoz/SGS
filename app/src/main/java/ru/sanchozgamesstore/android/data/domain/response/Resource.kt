package ru.sanchozgamesstore.android.data.domain.response

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val errorBody: ErrorBody?,
) {

    enum class Status {
        SUCCESS,
        LOADING,
        ERROR,
        NETWORK_ERROR;
    }

    /**
     * Флаг наличия [Status.ERROR] или [Status.NETWORK_ERROR]
     * */
    val hasError: Boolean
        get() = status == Status.ERROR || status == Status.NETWORK_ERROR

    fun <R> map(block: (T?) -> R?): Resource<R> {
        return Resource(status, block(data), errorBody)
    }

    companion object {
        fun <T> success(
            data: T? = null,
        ): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(
            errorBody: ErrorBody,
            data: T? = null,
        ): Resource<T> {
            return Resource(Status.ERROR, data, errorBody)
        }

        fun <T> error(
            message: String,
            data: T? = null,
        ): Resource<T> {
            return error(ErrorBody(message), data)
        }

        fun <T> loading(
            data: T? = null,
        ): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> networkError(
            errorBody: ErrorBody,
            data: T? = null,
        ): Resource<T> {
            return Resource(Status.NETWORK_ERROR, null, errorBody)
        }

    }
}
