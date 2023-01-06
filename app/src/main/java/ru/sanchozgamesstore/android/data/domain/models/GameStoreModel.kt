package ru.sanchozgamesstore.android.data.domain.models

import ru.sanchozgamesstore.R

/**
 * @param id id магазина
 * @param name название магазина
 * @param domain сайт магазина
 * @param games_count кол-во игр в магазине
 * @param icon иконка магазина
 * @param url старица игры в магазине
 * */
data class GameStoreModel(
    val id: Int,
    val name: String? = null,
    val domain: String? = null,
    val games_count: Int = 0,
    val icon: Int = R.drawable.ic_question_mark,
    val url: String? = null,
)
