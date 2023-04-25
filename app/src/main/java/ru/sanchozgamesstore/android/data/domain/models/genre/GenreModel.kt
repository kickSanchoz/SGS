package ru.sanchozgamesstore.android.data.domain.models.genre

/**
 * Доменоская модель. Жанр игры
 *
 * @param id id жанра
 * @param name название жанра
 * @param slug lowercase, no-space название жанра
 * @param _games_count кол-во игр, с данным жанром
 * @param image_background url картинки-заглушки
 * */
data class GenreModel(
    val id: Int,
    val name: String,
    val slug: String,
    private val _games_count: Int?,
    val image_background: String?,
) {
    val games_count: Int
        get() = _games_count ?: 0
}
