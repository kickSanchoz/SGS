package ru.sanchozgamesstore.android.data.domain.models.platform.requirements

/**
 * Доменовская модель. Требования игры от платформы
 *
 * @param minimum минимальные требования игры к платформе
 * @param recommended рекомендуемые требования игры к платформе
 * */
data class RequirementsModel(
    val minimum: String?,
    val recommended: String?,
)
