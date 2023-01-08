package ru.sanchozgamesstore.android.data.remote.models.platform.requirements

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.platform.requirements.RequirementsModel

/**
 * Серверная модель. Требования игры от платформы
 *
 * @param minimum минимальные требования игры к платформе
 * @param recommended рекомендуемые требования игры к платформе
 * */
@JsonClass(generateAdapter = true)
data class RequirementsApiModel(
    @Json(name = MINIMUM) val minimum: String?,
    @Json(name = RECOMMENDED) val recommended: String?,
) {
    fun toModel(): RequirementsModel {
        return RequirementsModel(
            minimum = minimum,
            recommended = recommended
        )
    }

    companion object {
        const val MINIMUM = "minimum"
        const val RECOMMENDED = "recommended"
    }
}
