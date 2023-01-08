package ru.sanchozgamesstore.android.data.remote.models.platform

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @param platform платформа БЕЗ указания конкретной модели
 * */
@JsonClass(generateAdapter = true)
data class ParentPlatformResponse(
    @Json(name = PLATFORM) val platform: ParentPlatformApiModel,
) {
    companion object {
        const val PLATFORM = "platform"
    }
}
