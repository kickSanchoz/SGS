package ru.sanchozgamesstore.android.data.domain.models.game

import ru.sanchozgamesstore.android.data.domain.enums.Store

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
    private val _id: Int?,
    private val _name: String?,
    val domain: String?,
    private val _games_count: Int?,
    private val _icon: Int?,
    val url: String? = null,
) {

    /**
     * id магазина
     * */
    val id: Int
        get() = _id ?: -1

    /**
     * название магазина
     * */
    val name: String
        get() = _name ?: "Unknown"

    /**
     * кол-во игр в магазине
     * */
    val games_count: Int
        get() = _games_count ?: 0

    /**
     * иконка магазина
     * */
    val icon: Int
        get() = _icon ?: Store.getStoreById(id).icon
}
