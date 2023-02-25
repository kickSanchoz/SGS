package ru.sanchozgamesstore.android.data.domain.models.user.businessCard

import androidx.annotation.StringRes
import ru.sanchozgamesstore.R

/**
 *
 * */
data class BusinessCardModel(
    val section: Section,
    val content: String?,
) {
    companion object {
        /**
         * Разделы визитной карточки профиля
         * */
        enum class Section {
            /**
             * Раздел никнейма
             * */
            USERNAME {
                override val title: Int = R.string.Username_lc
            },
            /**
             * Раздел полного имени
             * */
            FULLNAME {
                override val title: Int = R.string.Fullname_lc
            },
            /**
             * Раздел электронной почты
             * */
            EMAIL {
                override val title: Int = R.string.Email_lc
            };

            /**
             * Название раздела
             * */
            @get:StringRes
            abstract val title: Int
        }
    }
}