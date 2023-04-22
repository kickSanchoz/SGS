package ru.sanchozgamesstore.android.data.remote.models.store

import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse

@JsonClass(generateAdapter = true)
class StoresResponse : PaginationResponse<StoreApiModel>() {
    override var results: List<StoreApiModel>? = null
}
