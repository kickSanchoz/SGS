package ru.sanchozgamesstore.android.data.repository.game

import kotlinx.coroutines.delay
import ru.sanchozgamesstore.android.data.domain.enums.Store
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreBriefModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.models.game.GameDetailsApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.GameToStoreBriefApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.screenshot.ScreenshotApiModel

class GamesRepositoryLocalEmptyImpl : GamesRepository {

    override suspend fun getFavoriteGames(): Resource<List<GameDetailsModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGameDetails(
        id: Int
    ): Resource<GameDetailsModel> {
        val gameDetailsApi: GameDetailsApiModel = GameDetailsApiModel(
            id = 3498,
            name = "Grand Theft Auto V",
            name_original = "Grand Theft Auto V",
            slug = "grand-theft-auto-v",
            description = "<p>Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update. <br />\nSimultaneous storytelling from three unique perspectives: <br />\nFollow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from. <br />\nGTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.</p>",
            description_raw = "Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update. \nSimultaneous storytelling from three unique perspectives: \nFollow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from. \nGTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.",
            metacritic = 91,
            metacritic_platforms = null,
            released = null,
            tba = false,
            updated = "2023-01-06T09:07:02",
            background_image = null,
            background_image_additional = null,
            website = "http://www.rockstargames.com/V/",
            rating = 4.47,
            rating_top = 5,
            ratings = null,
            playtime = 72,
            screenshots_count = 57,
            movies_count = 8,
            achievements_count = 539,
            parent_achievements_count = 75,
            reviews_text_count = 78,
            ratings_count = 6072,
            reviews_count = 6150,
            saturated_color = "0f0f0f",
            dominant_color = "0f0f0f",
            parent_platforms = null,
            platforms = null,
            stores = null,
            developers = null,
            genres = null,
            tags = null,
            publishers = null,
        )

        val gameDetails = gameDetailsApi.toModel()

        delay(1000)

        return Resource.success(gameDetails)
    }

    override suspend fun getGameStores(
        id: Int
    ): Resource<List<GameToStoreModel>> {

        //Полученный с сервера список объектов игра_id - магазин_id
        val gameStores = listOf<GameToStoreBriefModel>(
            GameToStoreBriefModel(
                id = 290375,
                game_id = 3498,
                store_id = 3,
                url = "https://store.playstation.com/en-us/product/UP1004-CUSA00419_00-GTAVDIGITALDOWNL"
            ),
            GameToStoreBriefModel(
                id = 438095,
                game_id = 3498,
                store_id = 11,
                url = "https://www.epicgames.com/store/en-US/product/grand-theft-auto-v/home",
            ),
            GameToStoreBriefModel(
                id = 290376,
                game_id = 3498,
                store_id = 1,
                url = "http://store.steampowered.com/app/271590/",
            ),
            GameToStoreBriefModel(
                id = 290377,
                game_id = 3498,
                store_id = 7,
                url = "https://marketplace.xbox.com/en-US/Product/GTA-V/66acd000-77fe-1000-9115-d802545408a7",
            ),
            GameToStoreBriefModel(
                id = 290378,
                game_id = 3498,
                store_id = 2,
                url = "https://www.microsoft.com/en-us/store/p/grand-theft-auto-v/bpj686w6s0nh?cid=msft_web_chart",
            ),
        )

        val res: List<GameToStoreModel> = gameStores.map {
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

        delay(1000)

        return Resource.success(res)
    }

    override suspend fun getGameStores(
        id: Int,
        gameStores: List<StoreModel>
    ): Resource<List<GameToStoreModel>> {

        //Полученный с сервера список объектов игра_id - магазин_id
        val remoteStores = listOf<GameToStoreBriefApiModel>().map {
            it.toModel()
        }

        val res: List<GameToStoreModel> = remoteStores.map {
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

        delay(1000)

        return Resource.success(res)
    }

    override suspend fun getGameScreenshots(id: Int): Resource<List<ScreenshotModel>> {
        val apiScreenshots = listOf<ScreenshotApiModel>()

        val domainScreenshots = apiScreenshots.map { it.toModel() }

        delay(1000)
        return Resource.success(domainScreenshots)
    }
}