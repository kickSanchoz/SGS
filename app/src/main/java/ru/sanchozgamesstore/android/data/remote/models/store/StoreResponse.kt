package ru.sanchozgamesstore.android.data.remote.models.store

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreResponse(
    @Json(name = ID) val id: Int,
    @Json(name = URL) val url: String,
    @Json(name = STORE) val store: StoreApiModel,
) {
    companion object {
        const val ID = "id"
        const val URL = "url"
        const val STORE = "store"
    }
}
