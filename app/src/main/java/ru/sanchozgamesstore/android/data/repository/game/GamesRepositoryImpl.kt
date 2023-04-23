package ru.sanchozgamesstore.android.data.repository.game

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.enums.Store
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

    override suspend fun getGameDetails(id: Int): Resource<GameDetailsModel> = withContext(IO) {
        return@withContext gamesDataSource.getGameDetails(
            id = id,
        ).map {
            it?.toModel()
        }
    }

    override suspend fun getGameStores(id: Int): Resource<List<GameToStoreModel>> {
        val remoteStores = gamesDataSource.getGameStores(
            id = id
        ).map {
            it?.map { gameToStore ->
                gameToStore.toModel()
            }
        }

        val res = remoteStores.map { list ->
            list?.map {
                //Сохраненная информация по интересующему магазину
                val lStore = Store.getStoreById(it.store_id)

                GameToStoreModel(
                    _id = it.id,
                    _name = null,
                    domain = null,
                    _games_count = null,
                    _icon = lStore.icon,
                    url = it.url,
                )
            }
        }

        return res
    }

    override suspend fun getGameStores(
        id: Int,
        gameStores: List<StoreModel>
    ): Resource<List<GameToStoreModel>> {
        val remoteStores = gamesDataSource.getGameStores(
            id = id
        ).map {
            it?.map { gameToStore ->
                gameToStore.toModel()
            }
        }

        val res: Resource<List<GameToStoreModel>> = remoteStores.map { list ->
            list?.map {
                val rStore = gameStores.find { rStore ->
                    rStore.id == it.store_id
                }
                val lStore = Store.getStoreById(it.store_id)

                GameToStoreModel(
                    _id = rStore?.id,
                    _name = rStore?.name,
                    domain = rStore?.domain,
                    _games_count = rStore?.games_count,
                    _icon = lStore.icon,
                    url = it.url,
                )
            }
        }

        return res
    }

    override suspend fun getGameScreenshots(id: Int): Resource<List<ScreenshotModel>> =
        withContext(IO) {
            return@withContext gamesDataSource.getGameScreenshots(
                id = id,
            )
        }
}