package ru.sanchozgamesstore.android.data.remote.models.authorization

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.remote.BaseResponse

@JsonClass(generateAdapter = true)
data class AuthorizationResponse(
    @Json(name = KEY) val key: String
) : BaseResponse() {
    companion object {
        const val KEY = "key"
    }
}