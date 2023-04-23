package ru.sanchozgamesstore.android.data.remote.datasources.games

import ru.sanchozgamesstore.android.base.BaseDataSource
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.models.game.GameDetailsApiModel
import ru.sanchozgamesstore.android.data.remote.services.GamesService
import javax.inject.Inject

class GamesDataSource @Inject constructor(
    private val gamesService: GamesService,
) : BaseDataSource() {
    suspend fun getFavoriteGames(): Resource<List<GameDetailsApiModel>> = getResult {
        gamesService.getFavoriteGames()
    }.map {
        it?.results
    }
}