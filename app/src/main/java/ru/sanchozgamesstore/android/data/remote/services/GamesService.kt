package ru.sanchozgamesstore.android.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import ru.sanchozgamesstore.android.data.remote.annotations.InjectAccountToken
import ru.sanchozgamesstore.android.data.remote.models.game.FavoriteGamesResponse

interface GamesService {
    @GET("users/current/games")
    @InjectAccountToken
    suspend fun getFavoriteGames(): Response<FavoriteGamesResponse>
}