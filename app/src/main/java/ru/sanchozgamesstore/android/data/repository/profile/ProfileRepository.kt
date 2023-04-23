package ru.sanchozgamesstore.android.data.repository.profile

import androidx.lifecycle.LiveData
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
     * Получить данные пользователя из БД
     * */
    suspend fun getProfileLiveData(): LiveData<Resource<UserModel>>

    /**
     * Сохранить данные профиля
     * */
    suspend fun saveProfileLocal(userModel: UserModel)

    /**
     * Удалить профиль из приложения
     */
    suspend fun clearProfileLocalData(): Resource<Unit>
}