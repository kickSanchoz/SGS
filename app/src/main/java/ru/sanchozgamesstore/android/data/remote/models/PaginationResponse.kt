package ru.sanchozgamesstore.android.data.remote.models

import com.squareup.moshi.Json
import ru.sanchozgamesstore.android.data.remote.BaseResponse

abstract class PaginationResponse<T>: BaseResponse() {
    @Json(name = COUNT) var count: Int? = null
    @Json(name = NEXT) var next: String? = null
    @Json(name = PREVIOUS) var previous: String? = null
    @Json(name = RESULTS) abstract var results: List<T>?

    companion object {
        const val COUNT = "count"
        const val NEXT = "next"
        const val PREVIOUS = "previous"
        const val RESULTS = "results"
    }
}