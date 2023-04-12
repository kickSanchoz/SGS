package ru.sanchozgamesstore.android.data.repository.profile

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.models.user.toModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import ru.sanchozgamesstore.android.data.remote.models.user.UserApiModel
import javax.inject.Inject

class ProfileRepositoryLocalImpl @Inject constructor(
    private val accountTokenDataStore: AccountTokenDataStore,
) : ProfileRepository {

    override suspend fun login(
        authorizationModel: UserAuthorizationModel
    ): Resource<Unit> = withContext(IO) {
        setAccountToken("tempToken")

        Resource.success()
    }

    override suspend fun logout(): Resource<Unit> = withContext(IO) {
        deleteAccountToken()

        Resource.success()
    }

    override suspend fun getAccountToken(): String? = withContext(IO) {
        accountTokenDataStore.getAccountToken()
    }

    override suspend fun getAccountTokenLiveData(): LiveData<String> =
        accountTokenDataStore.getAccountTokenLiveData()


    override suspend fun setAccountToken(token: String): Unit = withContext(IO) {
        accountTokenDataStore.setAccountToken(token)
    }

    override suspend fun deleteAccountToken() {
        accountTokenDataStore.deleteAccountToken()
    }

    override suspend fun fetchProfile(): Resource<UserModel> {
        val userApiModel = UserApiModel(
            id = 665740,
            email = "testMail@mail.ru",
            username = "TestSanchoz",
            slug = "testsanchoz",
            full_name = "Aleksandr",
            avatar = "https://media.rawg.io/media/avatars/e79/e793bf121bc6c7292b0891d46a7f4b2d.jpg",
            bio = "",
            bio_raw = "",
            api_key = null,
            api_email = null,
            api_url = null,
            api_description = null,
            api_group = null,
            games_count = 4,
            games_wishlist_count = 0,
            collections_count = 0,
            reviews_count = 0,
            reviews_text_count = 0,
            comments_count = 0,
            steam_id = null,
            steam_id_status = null,
            steam_id_date = null,
            steam_id_confirm = false,
            gamer_tag = null,
            gamer_tag_status = null,
            gamer_tag_date = null,
            gamer_tag_confirm = false,
            psn_online_id = null,
            psn_online_id_status = null,
            psn_online_id_date = null,
            psn_online_id_confirm = false,
            gog = null,
            gog_status = null,
            gog_date = null,
            has_confirmed_accounts = false,
            game_background = null,
            following_count = 0,
            share_image = "https://media.rawg.io/media/api/images/users/25a/25a8e2e8a18aa43bfcb7470637980345_665740.jpg",
            subscribe_mail_synchronization = false,
            subscribe_mail_reviews_invite = true,
            subscribe_mail_recommendations = true,
            select_platform = false,
            token_program = false,
            tokens = "0.0000000000",
            is_staff = false,
            rated_games_percent = 0,
            is_editor = false,
            noindex = true,
            steam_id_locked = false,
            gamer_tag_locked = false,
            psn_online_id_locked = false,
            gog_locked = false,
            email_confirmed = true,
            can_edit_games = true,
            set_password = true,
        )

        delay(1000)
        return Resource.success(userApiModel.toModel())
    }

    override suspend fun getProfile(): Resource<UserModel> {
        val userApiModel = UserApiModel(
            id = 665740,
            email = "testMail@mail.ru",
            username = "TestSanchoz",
            slug = "testsanchoz",
            full_name = "Aleksandr",
            avatar = "https://media.rawg.io/media/avatars/e79/e793bf121bc6c7292b0891d46a7f4b2d.jpg",
            bio = "",
            bio_raw = "",
            api_key = null,
            api_email = null,
            api_url = null,
            api_description = null,
            api_group = null,
            games_count = 4,
            games_wishlist_count = 0,
            collections_count = 0,
            reviews_count = 0,
            reviews_text_count = 0,
            comments_count = 0,
            steam_id = null,
            steam_id_status = null,
            steam_id_date = null,
            steam_id_confirm = false,
            gamer_tag = null,
            gamer_tag_status = null,
            gamer_tag_date = null,
            gamer_tag_confirm = false,
            psn_online_id = null,
            psn_online_id_status = null,
            psn_online_id_date = null,
            psn_online_id_confirm = false,
            gog = null,
            gog_status = null,
            gog_date = null,
            has_confirmed_accounts = false,
            game_background = null,
            following_count = 0,
            share_image = "https://media.rawg.io/media/api/images/users/25a/25a8e2e8a18aa43bfcb7470637980345_665740.jpg",
            subscribe_mail_synchronization = false,
            subscribe_mail_reviews_invite = true,
            subscribe_mail_recommendations = true,
            select_platform = false,
            token_program = false,
            tokens = "0.0000000000",
            is_staff = false,
            rated_games_percent = 0,
            is_editor = false,
            noindex = true,
            steam_id_locked = false,
            gamer_tag_locked = false,
            psn_online_id_locked = false,
            gog_locked = false,
            email_confirmed = true,
            can_edit_games = true,
            set_password = true,
        )

        delay(1000)
        return Resource.success(userApiModel.toModel())
    }

    override suspend fun saveProfileLocal() {

    }
}