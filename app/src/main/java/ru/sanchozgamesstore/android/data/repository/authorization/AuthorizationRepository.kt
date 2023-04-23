package ru.sanchozgamesstore.android.data.repository.authorization

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
}