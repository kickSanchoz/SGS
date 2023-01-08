package ru.sanchozgamesstore.android.data.domain.models.developer

/**
 * Доменовская модель. Разработчик
 *
 * @param id id разработчика
 * @param name имя разработчика
 * @param slug lowercase, no-space имя разработчика
 * @param _games_count кол-во игр, созданных разработчиком
 * @param image_background url картинки-заглушки
 * */
data class DeveloperModel(
    val id: Int,
    val name: String,
    val slug: String,
    private val _games_count: Int?,
    val image_background: String,
) {
    val games_count: Int
        get() = _games_count ?: 0
}
