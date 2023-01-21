package ru.sanchozgamesstore.android.data.domain.models.platform

import androidx.annotation.ColorRes
import ru.sanchozgamesstore.R

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


    /**
     * Цвет в зависимости от рейтинга
     * */
    val colorByMetascore
        @ColorRes
        get() = when (metascore) {
            in 0..50 -> {
                R.color.red
            }
            in 51..79 -> {
                R.color.yellow
            }
            in 80..100 -> {
                R.color.green
            }
            else -> {
                R.color.white
            }
        }

}
