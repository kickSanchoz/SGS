package ru.sanchozgamesstore.android.data.repository.accountToken

import androidx.lifecycle.LiveData

interface AccountTokenRepository {
    /**
     * Пользователь авторизован
     * */
    suspend fun hasToken(): Boolean

    /**
     * Получить токен аккаунта
     * */
    suspend fun getAccountToken(): String?

    /**
     * Получить токен аккаунта в виде liveData
     * */
    suspend fun getAccountTokenLiveData(): LiveData<String?>

    /**
     * Установить токен аккаунта
     * */
    suspend fun setAccountToken(token: String): Unit

    /**
     * Удалить токен аккаунта из локального хранилища
     * */
    suspend fun deleteAccountToken(): Unit
}