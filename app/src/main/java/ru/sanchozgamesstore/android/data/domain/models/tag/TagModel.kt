package ru.sanchozgamesstore.android.data.domain.models.tag

/**
 * Доменовская модель. Тэг игры
 *
 * @param id id тэга
 * @param name название тэга
 * @param slug lowercase, no-space название тэга
 * @param language язык тэга
 * @param _games_count кол-во игр, с данным тэгом
 * @param image_background url картинки-заглушки
 * */
data class TagModel(
    val id: Int,
    val name: String,
    val slug: String,
    val language: String,
    val _games_count: Int?,
    val image_background: String,
) {
    val games_count: Int
        get() = _games_count ?: 0
}
