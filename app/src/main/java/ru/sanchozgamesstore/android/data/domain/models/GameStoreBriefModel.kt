package ru.sanchozgamesstore.android.data.domain.models

/**
 * @param id id связной таблицы
 * @param game_id id игры
 * @param store_id id магазина
 * @param url страница игры в интернет магазине
 * */
data class GameStoreBriefModel(
    val id: Int,
    val game_id: Int,
    val store_id: Int,
    val url: String
)
