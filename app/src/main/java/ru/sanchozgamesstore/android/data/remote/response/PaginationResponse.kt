package ru.sanchozgamesstore.android.data.remote.response

import com.squareup.moshi.Json

data class PaginationResponse<T>(
    @Json(name = COUNT) val count: Int,
    @Json(name = NEXT) val next: Int?,
    @Json(name = PREVIOUS) val previous: Int?,
    @Json(name = RESULTS) val results: List<T>,
) {
    companion object {
        const val COUNT = "count"
        const val NEXT = "next"
        const val PREVIOUS = "previous"
        const val RESULTS = "results"
    }
}