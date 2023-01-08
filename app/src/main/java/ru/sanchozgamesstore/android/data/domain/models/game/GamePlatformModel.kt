package ru.sanchozgamesstore.android.data.domain.models.game

import ru.sanchozgamesstore.android.data.domain.models.platform.PlatformModel
import ru.sanchozgamesstore.android.data.domain.models.platform.requirements.RequirementsModel

/**
 * Доменоская модель. Платформа для конкретной игры с датой релиза игры и требования игры к платформе
 *
 * @param platform платформа, на которой выпущена игра
 * @param released_at дата релиза игры на платформе
 * @param requirements требования игры к платформе
 * */
data class GamePlatformModel(
    val platform: PlatformModel,
    val released_at: String,
    val requirements: RequirementsModel?,
)
