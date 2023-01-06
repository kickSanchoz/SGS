package ru.sanchozgamesstore.android.data.domain.models

/**
 * @param id id магазина
 * @param name название магазина
 * @param slug "ленивое" название магазина
 * @param domain сайт магазина
 * @param games_count кол-во игр в магазина
 * @param image_background картинка-заглушка
 * */
data class StoreModel(
    val id: Int,
    val name: String,
    val slug: String,
    val domain: String,
    val games_count: Int,
    val image_background: String,
)
