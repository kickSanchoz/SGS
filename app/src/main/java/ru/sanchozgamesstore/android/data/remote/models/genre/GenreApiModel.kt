package ru.sanchozgamesstore.android.data.remote.models.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.genre.GenreModel

/**
 * Серверная модель. Жанр игры
 *
 * @param id id жанра
 * @param name название жанра
 * @param slug lowercase, no-space название жанра
 * @param games_count кол-во игр, с данным жанром
 * @param image_background url картинки-заглушки
 * */
@JsonClass(generateAdapter = true)
data class GenreApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = GAMES_COUNT) val games_count: Int?,
    @Json(name = IMAGE_BACKGROUND) val image_background: String?,
) {
    fun toModel(): GenreModel {
        return GenreModel(
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
