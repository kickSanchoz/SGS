package ru.sanchozgamesstore.android.data.domain.models.platform

/**
 * Доменовская модель. Оценка игры на Metacritic по конкретной платформе.
 *
 * @param _metascore оценка игры на metacritic
 * @param url url игры на metacritic по указанной платформе
 * @param platform плафторма, на которой была оценена игра
 * */
data class MetacriticPlatformModel(
    private val _metascore: Int?,
    val url: String?,
    val platform: PlatformBriefModel,
) {
    val metascore: Int
        get() = _metascore ?: 0
}
