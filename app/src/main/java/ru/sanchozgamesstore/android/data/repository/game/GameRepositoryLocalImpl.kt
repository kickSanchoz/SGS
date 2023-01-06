package ru.sanchozgamesstore.android.data.repository.game

import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.data.domain.enums.Store
import ru.sanchozgamesstore.android.data.domain.models.GameStoreBriefModel
import ru.sanchozgamesstore.android.data.domain.models.GameStoreModel
import ru.sanchozgamesstore.android.data.domain.models.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource

class GameRepositoryLocalImpl : GameRepository {
    override suspend fun getGameStores(
        id: Int
    ): Resource<List<GameStoreModel>> {
        val localStores = Store.values()

        val gameStores = listOf<GameStoreBriefModel>(
            GameStoreBriefModel(
                id = 290375,
                game_id = 3498,
                store_id = 3,
                url = "https://store.playstation.com/en-us/product/UP1004-CUSA00419_00-GTAVDIGITALDOWNL"
            ),
            GameStoreBriefModel(
                id = 438095,
                game_id = 3498,
                store_id = 11,
                url = "https://www.epicgames.com/store/en-US/product/grand-theft-auto-v/home",
            ),
            GameStoreBriefModel(
                id = 290376,
                game_id = 3498,
                store_id = 1,
                url = "http://store.steampowered.com/app/271590/",
            ),
            GameStoreBriefModel(
                id = 290377,
                game_id = 3498,
                store_id = 7,
                url = "https://marketplace.xbox.com/en-US/Product/GTA-V/66acd000-77fe-1000-9115-d802545408a7",
            ),
            GameStoreBriefModel(
                id = 290378,
                game_id = 3498,
                store_id = 2,
                url = "https://www.microsoft.com/en-us/store/p/grand-theft-auto-v/bpj686w6s0nh?cid=msft_web_chart",
            ),
        )

        val res: List<GameStoreModel> = gameStores.map {
            val lStore = localStores.find { lStore ->
                lStore.id == it.id
            }

            GameStoreModel(
                id = it.id,
                icon = lStore?.icon ?: R.drawable.ic_question_mark,
                url = it.url,
            )
        }

        return Resource.success(res)
    }

    override suspend fun getGameStores(
        id: Int,
        remoteStores: List<StoreModel>
    ): Resource<List<GameStoreModel>> {
        val localStores = Store.values()

        val gameStores = listOf<GameStoreBriefModel>(
            GameStoreBriefModel(
                id = 290375,
                game_id = 3498,
                store_id = 3,
                url = "https://store.playstation.com/en-us/product/UP1004-CUSA00419_00-GTAVDIGITALDOWNL"
            ),
            GameStoreBriefModel(
                id = 438095,
                game_id = 3498,
                store_id = 11,
                url = "https://www.epicgames.com/store/en-US/product/grand-theft-auto-v/home",
            ),
            GameStoreBriefModel(
                id = 290376,
                game_id = 3498,
                store_id = 1,
                url = "http://store.steampowered.com/app/271590/",
            ),
            GameStoreBriefModel(
                id = 290377,
                game_id = 3498,
                store_id = 7,
                url = "https://marketplace.xbox.com/en-US/Product/GTA-V/66acd000-77fe-1000-9115-d802545408a7",
            ),
            GameStoreBriefModel(
                id = 290378,
                game_id = 3498,
                store_id = 2,
                url = "https://www.microsoft.com/en-us/store/p/grand-theft-auto-v/bpj686w6s0nh?cid=msft_web_chart",
            ),
        )


        val fakeRemoteStores = listOf(
            StoreModel(
                id = 3,
                name = "PlayStation Store",
                slug = "playstation-store",
                domain = "store.playstation.com",
                games_count = 7786,
                image_background = "https://media.rawg.io/media/games/f87/f87457e8347484033cb34cde6101d08d.jpg",
            ),
            StoreModel(
                id = 11,
                name = "Epic Games",
                slug = "epic-games",
                domain = "epicgames.com",
                games_count = 1190,
                image_background = "https://media.rawg.io/media/games/c80/c80bcf321da44d69b18a06c04d942662.jpg",
            ),
            StoreModel(
                id = 1,
                name = "Steam",
                slug = "steam",
                domain = "store.steampowered.com",
                games_count = 70600,
                image_background = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
            ),
            StoreModel(
                id = 7,
                name = "Xbox 360 Store",
                slug = "xbox360",
                domain = "marketplace.xbox.com",
                games_count = 1911,
                image_background = "https://media.rawg.io/media/games/120/1201a40e4364557b124392ee50317b99.jpg",
            ),
            StoreModel(
                id = 2,
                name = "Xbox Store",
                slug = "xbox-store",
                domain = "microsoft.com",
                games_count = 4755,
                image_background = "https://media.rawg.io/media/games/4a0/4a0a1316102366260e6f38fd2a9cfdce.jpg",
            ),
        )

        val res: List<GameStoreModel> = gameStores.map {
            val rStore = fakeRemoteStores.find { rStore ->
                rStore.id == it.store_id
            }
            val lStore = localStores.find { lStore ->
                lStore.id == it.store_id
            }

            GameStoreModel(
                id = rStore?.id ?: 0,
                name = rStore?.name ?: "Unknown",
                domain = rStore?.domain,
                games_count = rStore?.games_count ?: 0,
                icon = lStore?.icon ?: R.drawable.ic_question_mark,
                url = it.url,
            )
        }

        return Resource.success(res)
    }
}