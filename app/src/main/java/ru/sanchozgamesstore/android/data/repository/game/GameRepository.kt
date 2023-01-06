package ru.sanchozgamesstore.android.data.repository.game

import ru.sanchozgamesstore.android.data.domain.models.GameStoreModel
import ru.sanchozgamesstore.android.data.domain.models.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource

interface GameRepository {

    suspend fun getGameStores(
        id: Int
    ): Resource<List<GameStoreModel>>

    suspend fun getGameStores(
        id: Int,
        remoteStores: List<StoreModel>
    ): Resource<List<GameStoreModel>>
}