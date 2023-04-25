package ru.sanchozgamesstore.android.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sanchozgamesstore.android.data.remote.annotations.InjectAccountToken
import ru.sanchozgamesstore.android.data.remote.annotations.InjectApiKey
import ru.sanchozgamesstore.android.data.remote.models.game.FavoriteGamesResponse
import ru.sanchozgamesstore.android.data.remote.models.game.GameDetailsApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.GamesResponse
import ru.sanchozgamesstore.android.data.remote.models.game.screenshot.GameToStoreBriefResponse
import ru.sanchozgamesstore.android.data.remote.models.game.screenshot.ScreenshotsResponse

interface GamesService {
    @GET("users/current/games")
    @InjectAccountToken
    suspend fun getFavoriteGames(): Response<FavoriteGamesResponse>

    @GET("games")
    @InjectApiKey
    suspend fun getGamesBySearch(
        @Query("page") page: Int,
        @Query("page_size") page_size: Int,
        @Query("search") search: String,
        @Query("ordering") ordering: String = "-rating"
    ): Response<GamesResponse>

    @GET("games/{id}")
    @InjectApiKey
    suspend fun getGameDetails(
        @Path("id") gameId: Int
    ): Response<GameDetailsApiModel>

    @GET("games/{game_pk}/stores")
    @InjectApiKey
    suspend fun getGameStores(
        @Path("game_pk") gameId: Int
    ): Response<GameToStoreBriefResponse>

    @GET("games/{game_pk}/screenshots")
    @InjectApiKey
    suspend fun getGameScreenshots(
        @Path("game_pk") gameId: Int
    ): Response<ScreenshotsResponse>
}