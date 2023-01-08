package ru.sanchozgamesstore.android.data.domain.models.platform

import ru.sanchozgamesstore.R

/**
 * Доменоская модель. Информация о платформе БЕЗ указания конкретной модели.
 *
 * Например: PC, PlayStation, XBOX.
 *
 * @param id id платформы
 * @param name название платформы
 * @param slug lowercase, no-space название платформы
 * @param platforms список платформ с указанием модели
 * @param _image drawable иконка родительской платформы
 * */
data class ParentPlatformModel(
    val id: Int,
    val name: String,
    val slug: String,
    val platforms: List<PlatformModel>?,
    private val _image: Int? = null,
) {
    val image: Int
        get() = _image ?: R.drawable.ic_question_mark
}