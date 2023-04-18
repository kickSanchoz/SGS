package ru.sanchozgamesstore.android.data.remote.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.sanchozgamesstore.android.data.remote.models.authorization.AuthorizationApiModel
import ru.sanchozgamesstore.android.data.remote.models.authorization.AuthorizationResponse

interface AuthorizationService {

    @POST("auth/login")
    suspend fun login(
        @Body authorizationApiModel: AuthorizationApiModel
    ): Response<AuthorizationResponse>
}