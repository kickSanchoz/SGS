package ru.sanchozgamesstore.android.data.domain.enums

import ru.sanchozgamesstore.R

/**
 * Доступные разделы метаданных
 * */
enum class GameMetaDataSection {
    PLATFORMS {
        override val title: Int = R.string.Platforms
    },
    GENRES {
        override val title: Int = R.string.Genres
    },
    DEVELOPERS {
        override val title: Int = R.string.Developers
    },
    PUBLISHERS {
        override val title: Int = R.string.Publishers
    };

    /**
     * Название раздела
     * */
    abstract val title: Int
}