package ru.sanchozgamesstore.android.data.repository.profile

import androidx.lifecycle.LiveData
import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.response.Resource

interface ProfileRepository {

    /**
     * Войти в аккаунт
     * */
    suspend fun login()

    /**
     * Выйти из аккаунта
     * */
    suspend fun logout()

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
    suspend fun getAccountTokenLiveData(): LiveData<String>

    /**
     * Установить токен аккаунта
     * */
    suspend fun setAccountToken(token: String)

    /**
     * Удалить токен аккаунта
     * */
    suspend fun deleteAccountToken()

    /**
     * Получить данные пользователя с сервера
     * */
    suspend fun fetchProfile(): Resource<UserModel>

    /**
     * Получить данные пользователя из БД
     * */
    suspend fun getProfile(): Resource<UserModel>

    /**
     * Сохранить данные профиля
     * */
    suspend fun saveProfileLocal()
}