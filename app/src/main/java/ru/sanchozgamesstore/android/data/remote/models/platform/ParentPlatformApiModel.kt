package ru.sanchozgamesstore.android.data.remote.models.platform

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.enums.ParentPlatform
import ru.sanchozgamesstore.android.data.domain.models.platform.ParentPlatformModel

/**
 * Серверная модель. Информация о платформе БЕЗ указания конкретной модели.
 *
 * Например: PC, PlayStation, XBOX.
 *
 * @param id id платформы
 * @param name название платформы
 * @param slug lowercase, no-space название платформы
 * @param platforms список платформ с указанием модели
 * */
@JsonClass(generateAdapter = true)
data class ParentPlatformApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = PLATFORMS) val platforms: List<PlatformApiModel>?,
) {
    fun toModel(): ParentPlatformModel {
        return ParentPlatformModel(
            id = id,
            name = name,
            slug = slug,
            platforms = platforms?.map { it.toModel() },
            _image = ParentPlatform.getParentPlatformById(id).icon
        )
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val SLUG = "slug"
        const val PLATFORMS = "platforms"
    }
}