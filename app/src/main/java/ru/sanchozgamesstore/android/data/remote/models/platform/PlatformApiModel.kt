package ru.sanchozgamesstore.android.data.remote.models.platform

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.platform.PlatformModel

/**
 * Серверная модель. Полная информация о платформе С указанием конкретной модели.
 *
 * Например: PlayStation 3, PlayStation 4, PlayStation 5, XBOX 360, XBOX One.
 *
 * @param id id платформы
 * @param name название платформы
 * @param slug lowercase, no-space название платформы
 * @param image url иконки платформы
 * @param year_end дата окончания производства
 * @param year_start дата начала производства
 * @param games_count кол-во игр на данной платформе
 * @param image_background url картинки-заглушки
 * */
@JsonClass(generateAdapter = true)
data class PlatformApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = IMAGE) val image: String?,
    @Json(name = YEAR_END) val year_end: Int?,
    @Json(name = YEAR_START) val year_start: Int?,
    @Json(name = GAMES_COUNT) val games_count: Int?,
    @Json(name = IMAGE_BACKGROUND) val image_background: String?,
) {
    fun toModel(): PlatformModel {
        return PlatformModel(
            id = id,
            name = name,
            slug = slug,
            image = image,
            year_end = year_end,
            year_start = year_start,
            _games_count = games_count,
            image_background = image_background,
        )
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val SLUG = "slug"
        const val IMAGE = "image"
        const val YEAR_END = "year_end"
        const val YEAR_START = "year_start"
        const val GAMES_COUNT = "games_count"
        const val IMAGE_BACKGROUND = "image_background"
    }
}
