package ru.sanchozgamesstore.android.data.domain.models.game.metadata

import ru.sanchozgamesstore.android.data.domain.enums.GameMetadataSection

/**
 * Доменовская модель. Раздел метаданных игры
 *
 * @param header название раздела
 * @param sequence перечисление элементов раздела в виде строки
 * */
data class GameMetadata(
    val header: GameMetadataSection,
    val sequence: String?,
)
