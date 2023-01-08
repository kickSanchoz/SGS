package ru.sanchozgamesstore.android.data.domain.models.platform

/**
 * Доменовская модель. Полная информация о платформе С указанием конкретной модели.
 *
 * Например: PlayStation 3, PlayStation 4, PlayStation 5, XBOX 360, XBOX One.
 *
 * @param id id платформы
 * @param name название платформы
 * @param slug lowercase, no-space название платформы
 * @param image url иконки платформы
 * @param year_end дата окончания производства
 * @param year_start дата начала производства
 * @param _games_count кол-во игр на данной платформе
 * @param image_background url картинки-заглушки
 * */
data class PlatformModel(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String?,
    val year_end: Int?,
    val year_start: Int?,
    private val _games_count: Int?,
    val image_background: String?,
) {
    val games_count: Int
        get() = _games_count ?: 0
}
