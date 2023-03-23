package ru.sanchozgamesstore.android.data.repository.game

import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource

interface GameRepository {

    /**
     * Получить список существующих игр
     * */
    suspend fun getFavoriteGames(): Resource<List<GameDetailsModel>>

    /**
     * Получить подробную информацию об игре
     *
     * @param id id игры, информацию о которой нужно получить
     * */
    suspend fun getGameDetail(
        id: Int,
    ): Resource<GameDetailsModel>

    /**
     * Получить магазины, на которых продаётся игра (БЕЗ НАЗВАНИЙ, ТОЛЬКО ССЫЛКИ НА СТРАНИЦЫ МАГАЗИНОВ).
     *
     * @param id id игры, магазины которого необходимо получить
     * */
    suspend fun getGameStores(
        id: Int
    ): Resource<List<GameToStoreModel>>

    /**
     * Получить магазины, на которых продаётся игра (С НАЗВАНИЕМ, ДОМЕНАМИ И ССЫЛКАМИ НА СТРАНИЦУ МАГАЗИНА).
     *
     * @param id id игры, магазины которого необходимо получить
     * @param remoteStores список магазинов, полученных из детальной информации об игре
     * */
    suspend fun getGameStores(
        id: Int,
        remoteStores: List<StoreModel>
    ): Resource<List<GameToStoreModel>>

    /**
     * Получить скриншоты игры
     *
     * @param id id игры, скриншоты которой необходимо получить
     * */
    suspend fun getGameScreenshots(
        id: Int,
    ): Resource<List<ScreenshotModel>>
}