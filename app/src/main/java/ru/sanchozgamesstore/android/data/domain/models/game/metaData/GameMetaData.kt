package ru.sanchozgamesstore.android.data.domain.models.game.metaData

import ru.sanchozgamesstore.android.data.domain.enums.GameMetaDataSection

/**
 * Доменовская модель. Раздел метаданных игры
 *
 * @param header название раздела
 * @param sequence перечисление элементов раздела в виде строки
 * */
data class GameMetaData(
    val header: GameMetaDataSection,
    val sequence: String?,
)
