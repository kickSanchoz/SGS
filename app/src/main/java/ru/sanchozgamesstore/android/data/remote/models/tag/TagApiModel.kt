package ru.sanchozgamesstore.android.data.remote.models.tag

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.tag.TagModel

/**
 * Серверная модель. Тэг игры
 *
 * @param id id тэга
 * @param name название тэга
 * @param slug lowercase, no-space название тэга
 * @param language язык тэга
 * @param games_count кол-во игр, с данным тэгом
 * @param image_background url картинки-заглушки
 * */
@JsonClass(generateAdapter = true)
data class TagApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = LANGUAGE) val language: String,
    @Json(name = GAMES_COUNT) val games_count: Int,
    @Json(name = IMAGE_BACKGROUND) val image_background: String,
) {
    fun toModel(): TagModel {
        return TagModel(
            id = id,
            name = name,
            slug = slug,
            language = language,
            _games_count = games_count,
            image_background = image_background,
        )
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val SLUG = "slug"
        const val LANGUAGE = "language"
        const val GAMES_COUNT = "games_count"
        const val IMAGE_BACKGROUND = "image_background"
    }
}
