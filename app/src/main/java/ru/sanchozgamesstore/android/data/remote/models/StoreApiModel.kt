package ru.sanchozgamesstore.android.data.remote.models

import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.StoreModel

/**
 * @param id id магазина
 * @param name название магазина
 * @param slug "ленивое" название магазина
 * @param domain сайт магазина
 * @param games_count кол-во игр в магазина
 * @param image_background картинка-заглушка
 * */
@JsonClass(generateAdapter = true)
data class StoreApiModel(
    val id: Int,
    val name: String,
    val slug: String,
    val domain: String,
    val games_count: Int,
    val image_background: String,
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
}

