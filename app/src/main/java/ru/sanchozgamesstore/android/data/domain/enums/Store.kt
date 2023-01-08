package ru.sanchozgamesstore.android.data.domain.enums

import ru.sanchozgamesstore.R

/**
 * Все доступные виды магазинов
 * */
enum class Store {
    STEAM {
        override val id: Int = 1
        override val icon: Int = R.drawable.ic_steam
    },
    XBOX {
        override val id: Int = 2
        override val icon: Int = R.drawable.ic_xbox
    },
    PLAYSTATION {
        override val id: Int = 3
        override val icon: Int = R.drawable.ic_playstation
    },
    APPSTORE {
        override val id: Int = 4
        override val icon: Int = R.drawable.ic_app_store
    },
    GOG {
        override val id: Int = 5
        override val icon: Int = R.drawable.ic_gog
    },
    NINTENDO {
        override val id: Int = 6
        override val icon: Int = R.drawable.ic_nintendo
    },
    XBOX360 {
        override val id: Int = 7
        override val icon: Int = R.drawable.ic_xbox
    },
    GOOGLEPLAY {
        override val id: Int = 8
        override val icon: Int = R.drawable.ic_google_play
    },
    ITCHIO {
        override val id: Int = 9
        override val icon: Int = R.drawable.ic_itch_io
    },
    EPICGAMES {
        override val id: Int = 11
        override val icon: Int = R.drawable.ic_egs
    },
    UNDEFINED {
        override val id: Int = -1
        override val icon: Int = R.drawable.ic_question_mark

    };

    abstract val id: Int
    abstract val icon: Int

    companion object {
        fun getStoreById(id: Int): Store {
            return values().find {
                it.id == id
            } ?: UNDEFINED
        }
    }
}