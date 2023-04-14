package ru.sanchozgamesstore.android.data.repository.profile

import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.response.Resource

interface ProfileRepository {
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