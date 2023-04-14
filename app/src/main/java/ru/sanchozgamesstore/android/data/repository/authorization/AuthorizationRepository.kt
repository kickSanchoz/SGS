package ru.sanchozgamesstore.android.data.repository.authorization

import androidx.lifecycle.LiveData
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.response.Resource

interface AuthorizationRepository {
    /**
     * Пользователь авторизован
     * */
    suspend fun isAuthorized(): Boolean

    /**
     * Войти в аккаунт
     * */
    suspend fun login(
        authorizationModel: UserAuthorizationModel,
    ): Resource<Unit>

    /**
     * Выйти из аккаунта
     * */
    suspend fun logout(): Resource<Unit>

    /**
     * TODO delete - Временный метод
     *
     * Получить токен аккаунта
     * */
    suspend fun getAccountToken(): String?

    /**
     * TODO delete - Временный метод
     *
     * Получить токен аккаунта в виде liveData
     * */
    suspend fun getAccountTokenLiveData(): LiveData<String?>

    /**
     * Установить токен аккаунта
     * */
    suspend fun setAccountToken(token: String): Unit

    /**
     * Удалить токен аккаунта
     * */
    suspend fun deleteAccountToken(): Unit
}