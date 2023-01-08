package ru.sanchozgamesstore.android.data.remote.models.store

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel

/**
 * Серверная модель. Интернет-магазин игр
 *
 * @param id id магазина
 * @param name название магазина
 * @param slug lowercase, no-space название магазина
 * @param domain сайт магазина
 * @param games_count кол-во игр в магазина
 * @param image_background url картинки-заглушки
 * */
@JsonClass(generateAdapter = true)
data class StoreApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = DOMAIN) val domain: String,
    @Json(name = GAMES_COUNT) val games_count: Int,
    @Json(name = IMAGE_BACKGROUND) val image_background: String,
) {
    fun toModel(): StoreModel {
        return StoreModel(
            id = id,
            name = name,
            slug = slug,
            domain = domain,
            games_count = games_count,
            image_background = image_background,
        )
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val SLUG = "slug"
        const val DOMAIN = "domain"
        const val GAMES_COUNT = "games_count"
        const val IMAGE_BACKGROUND = "image_background"
    }
}

