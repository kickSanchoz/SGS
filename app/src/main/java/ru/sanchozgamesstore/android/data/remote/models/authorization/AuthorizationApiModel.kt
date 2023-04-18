package ru.sanchozgamesstore.android.data.remote.models.authorization

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorizationApiModel(
    @Json(name = EMAIL) val email: String,
    @Json(name = PASSWORD) val password: String,
) {
    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}
