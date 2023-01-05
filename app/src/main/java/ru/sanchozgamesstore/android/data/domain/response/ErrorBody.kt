package ru.sanchozgamesstore.android.data.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorBody(
    @Json(name = MESSAGE) val message: String,
    @Json(name = ERRORS) val errors: Map<String, Array<String>>? = null,
    @Json(name = HTTP_CODE) var httpCode: Int? = null
) {
    companion object {
        const val MESSAGE = "message"
        const val ERRORS = "errors"
        const val HTTP_CODE = "httpCode"
    }
}