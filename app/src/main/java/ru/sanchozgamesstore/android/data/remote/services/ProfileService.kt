package ru.sanchozgamesstore.android.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import ru.sanchozgamesstore.android.data.remote.annotations.InjectAccountToken
import ru.sanchozgamesstore.android.data.remote.models.user.UserApiModel

interface ProfileService {
    @GET("users/current")
    @InjectAccountToken
    suspend fun fetchUser(): Response<UserApiModel>
}