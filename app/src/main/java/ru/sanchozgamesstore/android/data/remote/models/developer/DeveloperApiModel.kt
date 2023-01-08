package ru.sanchozgamesstore.android.data.remote.models.developer

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.developer.DeveloperModel

/**
 * Серверная модель. Разработчик
 *
 * @param id id разработчика
 * @param name имя разработчика
 * @param slug lowercase, no-space имя разработчика
 * @param games_count кол-во игр, созданных разработчиком
 * @param image_background url картинки-заглушки
 * */
@JsonClass(generateAdapter = true)
data class DeveloperApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = GAMES_COUNT) val games_count: Int,
    @Json(name = IMAGE_BACKGROUND) val image_background: String,
) {
    fun toModel(): DeveloperModel {
        return DeveloperModel(
            id = id,
            name = name,
            slug = slug,
            _games_count = games_count,
            image_background = image_background,
        )
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val SLUG = "slug"
        const val GAMES_COUNT = "games_count"
        const val IMAGE_BACKGROUND = "image_background"
    }
}