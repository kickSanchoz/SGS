package ru.sanchozgamesstore.android.data.remote.models.platform

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.platform.PlatformBriefModel

/**
 * Серверная модель. Краткая информация о платформе С указанием конкретной модели.
 *
 * Например: PlayStation 3, PlayStation 4, PlayStation 5, XBOX 360, XBOX One.
 *
 * @param id id платформы
 * @param name название платформы
 * @param slug lowercase, no-space название платформы
 * */
@JsonClass(generateAdapter = true)
data class PlatformBriefApiModel(
    @Json(name = PLATFORM) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
) {
    fun toModel(): PlatformBriefModel {
        return PlatformBriefModel(
            id = id,
            name = name,
            slug = slug,
        )
    }

    companion object {
        const val PLATFORM = "platform"
        const val NAME = "name"
        const val SLUG = "slug"
    }
}
