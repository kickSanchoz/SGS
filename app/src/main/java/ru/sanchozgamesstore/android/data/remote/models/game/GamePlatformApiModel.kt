package ru.sanchozgamesstore.android.data.remote.models.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.game.GamePlatformModel
import ru.sanchozgamesstore.android.data.remote.models.platform.PlatformApiModel
import ru.sanchozgamesstore.android.data.remote.models.platform.requirements.RequirementsApiModel

/**
 * Серверная модель. Платформа для конкретной игры с датой релиза игры и требования игры к платформе
 *
 * @param platform платформа, на которой выпущена игра
 * @param released_at дата релиза игры на платформе
 * @param requirements требования игры к платформе
 * */
@JsonClass(generateAdapter = true)
data class GamePlatformApiModel(
    @Json(name = PLATFORM) val platform: PlatformApiModel,
    @Json(name = RELEASED_AT) val released_at: String?,
    @Json(name = REQUIREMENTS) val requirements: RequirementsApiModel?,
) {
    fun toModel(): GamePlatformModel {
        return GamePlatformModel(
            platform = platform.toModel(),
            released_at = released_at,
            requirements = requirements?.toModel()
        )
    }

    companion object {
        const val PLATFORM = "platform"
        const val RELEASED_AT = "released_at"
        const val REQUIREMENTS = "requirements"
    }
}