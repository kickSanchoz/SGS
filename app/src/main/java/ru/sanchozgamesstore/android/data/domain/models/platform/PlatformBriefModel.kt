package ru.sanchozgamesstore.android.data.domain.models.platform

/**
 * Доменовская модель. Краткая информация о платформе С указанием конкретной модели.
 *
 * Например: PlayStation 3, PlayStation 4, PlayStation 5, XBOX 360, XBOX One.
 *
 * @param id id платформы
 * @param name название платформы
 * @param slug lowercase, no-space название платформы
 * */
data class PlatformBriefModel(
    val id: Int,
    val name: String,
    val slug: String,
)
