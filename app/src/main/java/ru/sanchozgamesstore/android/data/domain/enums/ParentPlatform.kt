package ru.sanchozgamesstore.android.data.domain.enums

import ru.sanchozgamesstore.R

/**
 * Все доступные виды родительских платформ
 *
 * Например, для PS2 и PS4 "родительская платформа" PlayStation
 * */
enum class ParentPlatform {
    PC {
        override val id: Int = 1
        override val icon: Int = R.drawable.ic_pc
    },
    PLAYSTATION {
        override val id: Int = 2
        override val icon: Int = R.drawable.ic_playstation
    },
    XBOX {
        override val id: Int = 3
        override val icon: Int = R.drawable.ic_xbox
    },
    IOS {
        override val id: Int = 4
        override val icon: Int = R.drawable.ic_apple
    },
    ANDROID {
        override val id: Int = 8
        override val icon: Int = R.drawable.ic_android
    },
    MAC {
        override val id: Int = 5
        override val icon: Int = R.drawable.ic_imac
    },
    LINUX {
        override val id: Int = 6
        override val icon: Int = R.drawable.ic_linux
    },
    NINTENDO {
        override val id: Int = 7
        override val icon: Int = R.drawable.ic_nintendo
    },
    ATARI {
        override val id: Int = 9
        override val icon: Int = R.drawable.ic_atari
    },
    AMIGA {
        override val id: Int = 10
        override val icon: Int = R.drawable.ic_amiga
    },
    SEGA {
        override val id: Int = 11
        override val icon: Int = R.drawable.ic_sega
    },
    THREEDO {
        override val id: Int = 12
        override val icon: Int = R.drawable.ic_3do
    },
    NEOGEO {
        override val id: Int = 13
        override val icon: Int = R.drawable.ic_neogeo
    },
    WEB {
        override val id: Int = 14
        override val icon: Int = R.drawable.ic_web
    },
    UNDEFINED {
        override val id: Int = -1
        override val icon: Int = R.drawable.ic_question_mark
    };

    abstract val id: Int
    abstract val icon: Int

    companion object {
        fun getParentPlatformById(id: Int): ParentPlatform {
            return values().find {
                it.id == id
            } ?: UNDEFINED
        }
    }
}