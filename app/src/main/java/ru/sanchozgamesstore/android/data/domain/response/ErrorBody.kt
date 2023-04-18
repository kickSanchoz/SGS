package ru.sanchozgamesstore.android.data.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorBody(
    @Json(name = MESSAGE) val message: String?,
    @Json(name = EMAIL) val email: List<String>? = null,
    @Json(name = PASSWORD) val password: List<String>? = null,
    @Json(name = HTTP_CODE) var httpCode: Int? = null
) {
    companion object {
        const val MESSAGE = "message"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val HTTP_CODE = "httpCode"
    }
}