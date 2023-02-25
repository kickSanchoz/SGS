package ru.sanchozgamesstore.android.data.remote.models.user

import com.squareup.moshi.Json

/**
 * Доменовская модель. Профиль пользователя
 *
 * @param id идентификатор пользователя
 * @param email электронная почта
 * @param username никнейм пользователя
 * @param slug lowercase, no-space никнейм пользователя
 * @param full_name полное имя пользователя
 * @param avatar url фотография пользователя
 * @param bio описание профиля с hmtl тэгами
 * @param bio_raw описания профиля без html тэгов
 * @param api_key api ключ документации rawg
 * @param api_email почта для api ключа
 * @param api_url ?
 * @param api_description описание для чего нужен api ключ
 * @param api_group категория api ключа
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
data class UserApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = EMAIL) val email: String,
    @Json(name = USERNAME) val username: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = FULL_NAME) val full_name: String?,
    @Json(name = AVATAR) val avatar: String?,
    @Json(name = BIO) val bio: String?,
    @Json(name = BIO_RAW) val bio_raw: String?,
    @Json(name = API_KEY) val api_key: String?,
    @Json(name = API_EMAIL) val api_email: String?,
    @Json(name = API_URL) val api_url: String?,
    @Json(name = API_DESCRIPTION) val api_description: String?,
    @Json(name = API_GROUP) val api_group: String?,
    @Json(name = GAMES_COUNT) val games_count: Int,
    @Json(name = GAMES_WISHLIST_COUNT) val games_wishlist_count: Int,
    @Json(name = COLLECTIONS_COUNT) val collections_count: Int,
    @Json(name = REVIEWS_COUNT) val reviews_count: Int,
    @Json(name = REVIEWS_TEXT_COUNT) val reviews_text_count: Int,
    @Json(name = COMMENTS_COUNT) val comments_count: Int,
    @Json(name = STEAM_ID) val steam_id: String?,
    @Json(name = STEAM_ID_STATUS) val steam_id_status: String?,
    @Json(name = STEAM_ID_DATE) val steam_id_date: String?,
    @Json(name = STEAM_ID_CONFIRM) val steam_id_confirm: Boolean,
    @Json(name = GAMER_TAG) val gamer_tag: String?,
    @Json(name = GAMER_TAG_STATUS) val gamer_tag_status: String?,
    @Json(name = GAMER_TAG_DATE) val gamer_tag_date: String?,
    @Json(name = GAMER_TAG_CONFIRM) val gamer_tag_confirm: Boolean,
    @Json(name = PSN_ONLINE_ID) val psn_online_id: String?,
    @Json(name = PSN_ONLINE_ID_STATUS) val psn_online_id_status: String?,
    @Json(name = PSN_ONLINE_ID_DATE) val psn_online_id_date: String?,
    @Json(name = PSN_ONLINE_ID_CONFIRM) val psn_online_id_confirm: Boolean,
    @Json(name = GOG) val gog: String?,
    @Json(name = GOG_STATUS) val gog_status: String?,
    @Json(name = GOG_DATE) val gog_date: String?,
    @Json(name = HAS_CONFIRMED_ACCOUNTS) val has_confirmed_accounts: Boolean,
    @Json(name = GAME_BACKGROUND) val game_background: String?,
    @Json(name = FOLLOWING_COUNT) val following_count: Int,
    @Json(name = SHARE_IMAGE) val share_image: String?,
    @Json(name = SUBSCRIBE_MAIL_SYNCHRONIZATION) val subscribe_mail_synchronization: Boolean,
    @Json(name = SUBSCRIBE_MAIL_REVIEWS_INVITE) val subscribe_mail_reviews_invite: Boolean,
    @Json(name = SUBSCRIBE_MAIL_RECOMMENDATIONS) val subscribe_mail_recommendations: Boolean,
    @Json(name = SELECT_PLATFORM) val select_platform: Boolean,
    @Json(name = TOKEN_PROGRAM) val token_program: Boolean,
    @Json(name = TOKENS) val tokens: String,
    @Json(name = IS_STAFF) val is_staff: Boolean,
    @Json(name = RATED_GAMES_PERCENT) val rated_games_percent: Int,
    @Json(name = IS_EDITOR) val is_editor: Boolean,
    @Json(name = NOINDEX) val noindex: Boolean,
    @Json(name = STEAM_ID_LOCKED) val steam_id_locked: Boolean,
    @Json(name = GAMER_TAG_LOCKED) val gamer_tag_locked: Boolean,
    @Json(name = PSN_ONLINE_ID_LOCKED) val psn_online_id_locked: Boolean,
    @Json(name = GOG_LOCKED) val gog_locked: Boolean,
    @Json(name = EMAIL_CONFIRMED) val email_confirmed: Boolean,
    @Json(name = CAN_EDIT_GAMES) val can_edit_games: Boolean,
    @Json(name = SET_PASSWORD) val set_password: Boolean,
) {
    companion object {
        const val ID = "id"
        const val EMAIL = "email"
        const val USERNAME = "username"
        const val SLUG = "slug"
        const val FULL_NAME = "full_name"
        const val AVATAR = "avatar"
        const val BIO = "bio"
        const val GAMES_COUNT = "games_count"
        const val GAMES_WISHLIST_COUNT = "games_wishlist_count"
        const val COLLECTIONS_COUNT = "collections_count"
        const val REVIEWS_COUNT = "reviews_count"
        const val REVIEWS_TEXT_COUNT = "reviews_text_count"
        const val COMMENTS_COUNT = "comments_count"
        const val STEAM_ID = "steam_id"
        const val STEAM_ID_STATUS = "steam_id_status"
        const val STEAM_ID_DATE = "steam_id_date"
        const val STEAM_ID_CONFIRM = "steam_id_confirm"
        const val GAMER_TAG = "gamer_tag"
        const val GAMER_TAG_STATUS = "gamer_tag_status"
        const val GAMER_TAG_DATE = "gamer_tag_date"
        const val GAMER_TAG_CONFIRM = "gamer_tag_confirm"
        const val PSN_ONLINE_ID = "psn_online_id"
        const val PSN_ONLINE_ID_STATUS = "psn_online_id_status"
        const val PSN_ONLINE_ID_DATE = "psn_online_id_date"
        const val PSN_ONLINE_ID_CONFIRM = "psn_online_id_confirm"
        const val GOG = "gog"
        const val GOG_STATUS = "gog_status"
        const val GOG_DATE = "gog_date"
        const val HAS_CONFIRMED_ACCOUNTS = "has_confirmed_accounts"
        const val GAME_BACKGROUND = "game_background"
        const val FOLLOWING_COUNT = "following_count"
        const val SHARE_IMAGE = "share_image"
        const val SUBSCRIBE_MAIL_SYNCHRONIZATION = "subscribe_mail_synchronization"
        const val SUBSCRIBE_MAIL_REVIEWS_INVITE = "subscribe_mail_reviews_invite"
        const val SUBSCRIBE_MAIL_RECOMMENDATIONS = "subscribe_mail_recommendations"
        const val SELECT_PLATFORM = "select_platform"
        const val TOKEN_PROGRAM = "token_program"
        const val TOKENS = "tokens"
        const val IS_STAFF = "is_staff"
        const val RATED_GAMES_PERCENT = "rated_games_percent"
        const val API_KEY = "api_key"
        const val API_EMAIL = "api_email"
        const val API_URL = "api_url"
        const val API_DESCRIPTION = "api_description"
        const val API_GROUP = "api_group"
        const val IS_EDITOR = "is_editor"
        const val NOINDEX = "noindex"
        const val BIO_RAW = "bio_raw"
        const val STEAM_ID_LOCKED = "steam_id_locked"
        const val GAMER_TAG_LOCKED = "gamer_tag_locked"
        const val PSN_ONLINE_ID_LOCKED = "psn_online_id_locked"
        const val GOG_LOCKED = "gog_locked"
        const val EMAIL_CONFIRMED = "email_confirmed"
        const val CAN_EDIT_GAMES = "can_edit_games"
        const val SET_PASSWORD = "set_password"
    }
}
