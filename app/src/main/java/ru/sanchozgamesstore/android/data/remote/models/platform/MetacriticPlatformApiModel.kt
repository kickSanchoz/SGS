package ru.sanchozgamesstore.android.data.remote.models.platform

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.platform.MetacriticPlatformModel

/**
 * Серверная модель. Оценка игры на Metacritic по конкретной платформе.
 *
 * @param metascore оценка игры на metacritic
 * @param url url игры на metacritic по указанной платформе
 * @param platform плафторма, на которой была оценена игра
 * */
@JsonClass(generateAdapter = true)
data class MetacriticPlatformApiModel(
    @Json(name = METASCORE) val metascore: Int?,
    @Json(name = URL) val url: String?,
    @Json(name = PLATFORM) val platform: PlatformBriefApiModel,
) {
    fun toModel(): MetacriticPlatformModel {
        return MetacriticPlatformModel(
            _metascore = metascore,
            url = url,
            platform = platform.toModel(),
        )
    }

    companion object {
        const val METASCORE = "metascore"
        const val URL = "url"
        const val PLATFORM = "platform"
    }
}