package ru.sanchozgamesstore.android.data.repository.game

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.datasources.games.GamesDataSource
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesDataSource: GamesDataSource,
) : GamesRepository {
    override suspend fun getFavoriteGames(): Resource<List<GameDetailsModel>> = withContext(IO) {
        return@withContext gamesDataSource.getFavoriteGames().map { list ->
            list?.map {
                it.toModel()
            }
        }
    }

    override suspend fun getGameDetail(id: Int): Resource<GameDetailsModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getGameStores(id: Int): Resource<List<GameToStoreModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGameStores(
        id: Int,
        remoteStores: List<StoreModel>
    ): Resource<List<GameToStoreModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGameScreenshots(id: Int): Resource<List<ScreenshotModel>> {
        TODO("Not yet implemented")
    }
}