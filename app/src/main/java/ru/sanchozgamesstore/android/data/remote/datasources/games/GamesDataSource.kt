package ru.sanchozgamesstore.android.data.remote.datasources.games

import ru.sanchozgamesstore.android.base.BaseDataSource
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.models.game.GameDetailsApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.GameToStoreBriefApiModel
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

    suspend fun getGamesBySearch(
        page: Int,
        page_size: Int,
        search: String,
    ) = getResult {
        gamesService.getGamesBySearch(
            page = page,
            page_size = page_size,
            search = search,
        )
    }

    suspend fun getGameDetails(id: Int): Resource<GameDetailsApiModel> = getResult {
        gamesService.getGameDetails(
            gameId = id,
        )
    }

    suspend fun getGameStores(id: Int): Resource<List<GameToStoreBriefApiModel>> = getResult {
        gamesService.getGameStores(
            gameId = id
        )
    }.map {
        it?.results
    }

    suspend fun getGameScreenshots(id: Int): Resource<List<ScreenshotModel>> = getResult {
        gamesService.getGameScreenshots(
            gameId = id,
        )
    }.map {
        it?.results?.map { screenshot ->
            screenshot.toModel()
        }
    }
}