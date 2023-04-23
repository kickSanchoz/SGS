package ru.sanchozgamesstore.android.data.local.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель БД. Профиль пользователя
 *
 * @param id идентификатор пользователя
 * @param email электронная почта
 * @param username никнейм пользователя
 * @param slug lowercase, no-space никнейм пользователя
 * @param full_name полное имя пользователя
 * @param avatar url фотография пользователя
 * @param bio описание профиля с hmtl тэгами
 * @param bio_raw описания профиля без html тэгов
 * @param games_count кол-во игр, добавленных пользователем
 * @param games_wishlist_count кол-во игр в списке желаний
 * @param collections_count кол-во коллекций пользователя
 * @param reviews_count кол-во обзоров, оставленных пользователем
 * @param reviews_text_count кол-во текстовых обзоров, оставленных пользователем
 * @param comments_count кол-во оставленных комментариев
 * @param steam_id id профиля Steam
 * @param steam_id_status статус профиля Steam
 * @param steam_id_date ?
 * @param steam_id_confirm подтвержденный профиль Steam
 * @param gamer_tag id профиля Gamer
 * @param gamer_tag_status статус профиля Gamer
 * @param gamer_tag_date ?
 * @param gamer_tag_confirm подтвержденный профиль Gamer
 * @param psn_online_id id профиля PSN
 * @param psn_online_id_status статус профиля PSN
 * @param psn_online_id_date ?
 * @param psn_online_id_confirm подтвержденный профиль PSN
 * @param gog id профиля GOG
 * @param gog_status статус профиля GOG
 * @param gog_date ?
 * @param has_confirmed_accounts есть подтвержденные аккаунты
 * @param game_background фон профиля
 * @param following_count кол-во отслеживаемых игр
 * @param share_image сгенерированное изображение для распространения
 * @param subscribe_mail_synchronization ?
 * @param subscribe_mail_reviews_invite ?
 * @param subscribe_mail_recommendations ?
 * @param select_platform ?
 * @param token_program ?
 * @param tokens ?
 * @param is_staff сотрудник rawg
 * @param rated_games_percent процент оцененных игр пользователя
 * @param is_editor редактор rawg
 * @param noindex ?
 * @param steam_id_locked ?
 * @param gamer_tag_locked ?
 * @param psn_online_id_locked ?
 * @param gog_locked ?
 * @param email_confirmed почта подтверждена
 * @param can_edit_games может редактировать игры
 * @param set_password пароль установлен
 * */
@Entity(tableName = "user")
data class UserRoomModel(
    @PrimaryKey
    val id: Int,
    val email: String,
    val username: String,
    val slug: String,
    val full_name: String?,
    val avatar: String?,
    val bio: String?,
    val bio_raw: String?,
    val games_count: Int,
    val games_wishlist_count: Int,
    val collections_count: Int,
    val reviews_count: Int,
    val reviews_text_count: Int,
    val comments_count: Int,
    val steam_id: String?,
    val steam_id_status: String?,
    val steam_id_date: String?,
    val steam_id_confirm: Boolean,
    val gamer_tag: String?,
    val gamer_tag_status: String?,
    val gamer_tag_date: String?,
    val gamer_tag_confirm: Boolean,
    val psn_online_id: String?,
    val psn_online_id_status: String?,
    val psn_online_id_date: String?,
    val psn_online_id_confirm: Boolean,
    val gog: String?,
    val gog_status: String?,
    val gog_date: String?,
    val has_confirmed_accounts: Boolean,
    val game_background: String?,
    val following_count: Int,
    val share_image: String?,
    val subscribe_mail_synchronization: Boolean,
    val subscribe_mail_reviews_invite: Boolean,
    val subscribe_mail_recommendations: Boolean,
    val select_platform: Boolean,
    val token_program: Boolean,
    val tokens: String,
    val is_staff: Boolean,
    val rated_games_percent: Int,
    val is_editor: Boolean,
    val noindex: Boolean,
    val steam_id_locked: Boolean,
    val gamer_tag_locked: Boolean,
    val psn_online_id_locked: Boolean,
    val gog_locked: Boolean,
    val email_confirmed: Boolean,
    val can_edit_games: Boolean,
    val set_password: Boolean,
)