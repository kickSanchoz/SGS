package ru.sanchozgamesstore.android.data.remote.pagingsources

import androidx.paging.PagingConfig
import ru.sanchozgamesstore.android.base.BasePagingSource
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.datasources.games.GamesDataSource
import ru.sanchozgamesstore.android.data.remote.models.game.GameDetailsApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.GamesResponse
import javax.inject.Inject

class GamesPagingSource @Inject constructor(
    private val gamesDataSource: GamesDataSource,
    private val search: String
) : BasePagingSource<GameDetailsModel, GameDetailsApiModel, GamesResponse>() {

    override fun getPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
            enablePlaceholders = true,
        )
    }

    override suspend fun onLoad(
        pageNumber: Int,
        pageSize: Int
    ): Resource<GamesResponse> {
        return gamesDataSource.getGamesBySearch(
            page = pageNumber,
            page_size = pageSize,
            search = search,
        )
    }

    override fun convert(response: GamesResponse): List<GameDetailsModel> {
        return response.results?.map {
            it.toModel()
        } ?: emptyList()
    }

}