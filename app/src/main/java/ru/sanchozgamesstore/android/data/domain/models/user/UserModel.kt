package ru.sanchozgamesstore.android.data.domain.models.user

import ru.sanchozgamesstore.android.data.domain.models.user.businessCard.BusinessCardModel
import ru.sanchozgamesstore.android.data.domain.models.user.businessCard.BusinessCardModel.Companion.Section
import ru.sanchozgamesstore.android.data.remote.models.user.UserApiModel

/**
 * Серверная модель. Профиль пользователя
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
data class UserModel(
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
) {
    val businessCardSections = listOf(
        BusinessCardModel(
            section = Section.USERNAME,
            content = username,
        ),
        BusinessCardModel(
            section = Section.FULLNAME,
            content = full_name,
        ),
        BusinessCardModel(
            section = Section.EMAIL,
            content = email,
        ),
    ).filter {
        it.content != null
    }
}

fun UserApiModel.toModel(): UserModel {
    return UserModel(
        id = id,
        email = email,
        username = username,
        slug = slug,
        full_name = full_name,
        avatar = avatar,
        bio = bio,
        bio_raw = bio_raw,
        games_count = games_count,
        games_wishlist_count = games_wishlist_count,
        collections_count = collections_count,
        reviews_count = reviews_count,
        reviews_text_count = reviews_text_count,
        comments_count = comments_count,
        steam_id = steam_id,
        steam_id_status = steam_id_status,
        steam_id_date = steam_id_date,
        steam_id_confirm = steam_id_confirm,
        gamer_tag = gamer_tag,
        gamer_tag_status = gamer_tag_status,
        gamer_tag_date = gamer_tag_date,
        gamer_tag_confirm = gamer_tag_confirm,
        psn_online_id = psn_online_id,
        psn_online_id_status = psn_online_id_status,
        psn_online_id_date = psn_online_id_date,
        psn_online_id_confirm = psn_online_id_confirm,
        gog = gog,
        gog_status = gog_status,
        gog_date = gog_date,
        has_confirmed_accounts = has_confirmed_accounts,
        game_background = game_background,
        following_count = following_count,
        share_image = share_image,
        subscribe_mail_synchronization = subscribe_mail_synchronization,
        subscribe_mail_reviews_invite = subscribe_mail_reviews_invite,
        subscribe_mail_recommendations = subscribe_mail_recommendations,
        select_platform = select_platform,
        token_program = token_program,
        tokens = tokens,
        is_staff = is_staff,
        rated_games_percent = rated_games_percent,
        is_editor = is_editor,
        noindex = noindex,
        steam_id_locked = steam_id_locked,
        gamer_tag_locked = gamer_tag_locked,
        psn_online_id_locked = psn_online_id_locked,
        gog_locked = gog_locked,
        email_confirmed = email_confirmed,
        can_edit_games = can_edit_games,
        set_password = set_password,
    )
}