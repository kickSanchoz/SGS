package ru.sanchozgamesstore.android.data.remote.models

import com.squareup.moshi.Json

abstract class PaginationResponse<T> {
    @Json(name = COUNT) var count: Int? = null
    @Json(name = NEXT) var next: Int? = null
    @Json(name = PREVIOUS) var previous: Int? = null
    @Json(name = RESULTS) abstract var results: List<T>?

    companion object {
        const val COUNT = "count"
        const val NEXT = "next"
        const val PREVIOUS = "previous"
        const val RESULTS = "results"
    }
}