package ru.sanchozgamesstore.android.data.domain.models.game

import ru.sanchozgamesstore.R

/**
 * Доменовская модель. Информация о магазине для конкретной игры
 *
 * @param _id id магазина
 * @param _name название магазина
 * @param domain сайт магазина
 * @param _games_count кол-во игр в магазине
 * @param _icon иконка магазина
 * @param url старица игры в магазине
 * */
data class GameToStoreModel(
    val _id: Int?,
    val _name: String?,
    val domain: String?,
    val _games_count: Int?,
    val _icon: Int?,
    val url: String? = null,
) {
    val id: Int
        get() = _id ?: -1

    val name: String
        get() = _name ?: "Unknown"

    val games_count: Int
        get() = _games_count ?: 0

    val icon: Int
        get() = R.drawable.ic_question_mark
}
