package ru.sanchozgamesstore.android.data.domain.enums

import ru.sanchozgamesstore.R

/**
 * Разновидность рейтинга
 * */
enum class RatingSpecies {
    EXCEPTIONAL {
        override val id: Int = 5
        override val title: String = "Exceptional"
        override val color: Int = R.color.green2
    },
    RECOMMENDED {
        override val id: Int = 4
        override val title: String = "Recommended"
        override val color: Int = R.color.blue
    },
    MEH {
        override val id: Int = 3
        override val title: String = "Meh"
        override val color: Int = R.color.orange
    },
    SKIP {
        override val id: Int = 1
        override val title: String = "Skip"
        override val color: Int = R.color.red2
    },
    UNDEFINED {
        override val id: Int = -1
        override val title: String = RatingSpecies.DEFAULT_TITLE
        override val color: Int = R.color.grey
    };

    abstract val id: Int
    abstract val title: String
    abstract val color: Int

    companion object {
        const val DEFAULT_TITLE = "Unknown"

        /**
         * Тип рейтинга по ключу, если такого не было найдено, то в качестве ключа
         * установить UNDEFINED
         */
        fun getRatingSpeciesById(id: Int): RatingSpecies {
            return values().find {
                it.id == id
            } ?: UNDEFINED
        }
    }
}