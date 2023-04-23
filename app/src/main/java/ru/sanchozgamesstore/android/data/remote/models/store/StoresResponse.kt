package ru.sanchozgamesstore.android.data.remote.models.store

import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse

@JsonClass(generateAdapter = true)
data class StoresResponse(
    override var results: List<StoreApiModel>?
) : PaginationResponse<StoreApiModel>() {

}
