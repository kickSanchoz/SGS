package ru.sanchozgamesstore.android.data.domain.models.publisher

/**
 * Доменовская модель. Издатель
 *
 * @param id id издателя
 * @param name имя издателя
 * @param slug lowercase, no-space имя издателя
 * @param _games_count кол-во игр, выпущенных данным издателем
 * @param image_background url картинки-заглушки
 * */
class PublisherModel(
    val id: Int,
    val name: String,
    val slug: String,
    private val _games_count: Int?,
    val image_background: String?,
) {
    val games_count: Int
        get() = _games_count ?: 0
}