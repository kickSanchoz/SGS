package ru.sanchozgamesstore.android.data.repository.game

import kotlinx.coroutines.delay
import ru.sanchozgamesstore.android.data.domain.enums.Store
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreBriefModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.screnshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.models.developer.DeveloperApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.GameDetailsApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.GamePlatformApiModel
import ru.sanchozgamesstore.android.data.remote.models.game.screenshot.ScreenshotApiModel
import ru.sanchozgamesstore.android.data.remote.models.genre.GenreApiModel
import ru.sanchozgamesstore.android.data.remote.models.platform.*
import ru.sanchozgamesstore.android.data.remote.models.platform.requirements.RequirementsApiModel
import ru.sanchozgamesstore.android.data.remote.models.publisher.PublisherApiModel
import ru.sanchozgamesstore.android.data.remote.models.rating.RatingApiModel
import ru.sanchozgamesstore.android.data.remote.models.store.StoreApiModel
import ru.sanchozgamesstore.android.data.remote.models.store.StoreResponse
import ru.sanchozgamesstore.android.data.remote.models.tag.TagApiModel

class GameRepositoryLocalImpl : GameRepository {
    override suspend fun getGameDetail(
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
            metacritic_platforms = listOf<MetacriticPlatformApiModel>(
                MetacriticPlatformApiModel(
                    metascore = 79,
                    url = "https://www.metacritic.com/game/xbox-series-x/grand-theft-auto-v",
                    PlatformBriefApiModel(
                        id = 186,
                        name = "Xbox Series S/X",
                        slug = "xbox-series-x",
                    )
                ),
                MetacriticPlatformApiModel(
                    metascore = 81,
                    url = "https://www.metacritic.com/game/playstation-5/grand-theft-auto-v",
                    PlatformBriefApiModel(
                        id = 187,
                        name = "PlayStation 5",
                        slug = "playstation5",
                    )
                ),
                MetacriticPlatformApiModel(
                    metascore = 97,
                    url = "https://www.metacritic.com/game/playstation-3/grand-theft-auto-v",
                    PlatformBriefApiModel(
                        id = 16,
                        name = "PlayStation 3",
                        slug = "playstation3",
                    )
                ),
                MetacriticPlatformApiModel(
                    metascore = 96,
                    url = "https://www.metacritic.com/game/pc/grand-theft-auto-v",
                    PlatformBriefApiModel(
                        id = 4,
                        name = "PC",
                        slug = "pc",
                    )
                ),
                MetacriticPlatformApiModel(
                    metascore = 97,
                    url = "https://www.metacritic.com/game/xbox-360/grand-theft-auto-v",
                    PlatformBriefApiModel(
                        id = 14,
                        name = "Xbox 360",
                        slug = "xbox360",
                    )
                ),
                MetacriticPlatformApiModel(
                    metascore = 97,
                    url = "https://www.metacritic.com/game/xbox-one/grand-theft-auto-v",
                    PlatformBriefApiModel(
                        id = 1,
                        name = "Xbox One",
                        slug = "xbox-one",
                    )
                ),
            ),
            released = "2013-09-17",
            tba = false,
            updated = "2023-01-06T09:07:02",
            background_image = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
            background_image_additional = "https://media.rawg.io/media/screenshots/5f5/5f5a38a222252d996b18962806eed707.jpg",
            website = "http://www.rockstargames.com/V/",
            rating = 4.47,
            rating_top = 5,
            ratings = listOf<RatingApiModel>(
                RatingApiModel(
                    id = 5,
                    title = "exceptional",
                    count = 3630,
                    percent = 59.02,
                ),
                RatingApiModel(
                    id = 4,
                    title = "recommended",
                    count = 2020,
                    percent = 32.85,
                ),
                RatingApiModel(
                    id = 3,
                    title = "meh",
                    count = 388,
                    percent = 6.31,
                ),
                RatingApiModel(
                    id = 1,
                    title = "skip",
                    count = 112,
                    percent = 1.82,
                ),
            ),
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
            parent_platforms = listOf<ParentPlatformResponse>(
                ParentPlatformResponse(
                    platform = ParentPlatformApiModel(
                        id = 1,
                        name = "PC",
                        slug = "pc",
                        platforms = null
                    )
                ),
                ParentPlatformResponse(
                    platform = ParentPlatformApiModel(
                        id = 2,
                        name = "PlayStation",
                        slug = "playstation",
                        platforms = null
                    )
                ),
                ParentPlatformResponse(
                    platform = ParentPlatformApiModel(
                        id = 3,
                        name = "Xbox",
                        slug = "xbox",
                        platforms = null
                    )
                ),
            ),
            platforms = listOf<GamePlatformApiModel>(
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 187,
                        name = "PlayStation 5",
                        slug = "playstation5",
                        image = null,
                        year_end = null,
                        year_start = 2020,
                        games_count = 766,
                        image_background = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = null
                ),
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 186,
                        name = "Xbox Series S/X",
                        slug = "xbox-series-x",
                        image = null,
                        year_end = null,
                        year_start = 2020,
                        games_count = 690,
                        image_background = "https://media.rawg.io/media/games/082/082365507ff04d456c700157072d35db.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = null
                ),
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 18,
                        name = "PlayStation 4",
                        slug = "playstation4",
                        image = null,
                        year_end = null,
                        year_start = null,
                        games_count = 6540,
                        image_background = "https://media.rawg.io/media/games/b72/b7233d5d5b1e75e86bb860ccc7aeca85.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = null
                ),
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 4,
                        name = "PC",
                        slug = "pc",
                        image = null,
                        year_end = null,
                        year_start = null,
                        games_count = 512367,
                        image_background = "https://media.rawg.io/media/games/4be/4be6a6ad0364751a96229c56bf69be59.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = RequirementsApiModel(
                        minimum = "Minimum:OS: Windows 10 64 Bit, Windows 8.1 64 Bit, Windows 8 64 Bit, Windows 7 64 Bit Service Pack 1, Windows Vista 64 Bit Service Pack 2* (*NVIDIA video card recommended if running Vista OS)Processor: Intel Core 2 Quad CPU Q6600 @ 2.40GHz (4 CPUs) / AMD Phenom 9850 Quad-Core Processor (4 CPUs) @ 2.5GHzMemory: 4 GB RAMGraphics: NVIDIA 9800 GT 1GB / AMD HD 4870 1GB (DX 10, 10.1, 11)Storage: 72 GB available spaceSound Card: 100% DirectX 10 compatibleAdditional Notes: Over time downloadable content and programming changes will change the system requirements for this game.  Please refer to your hardware manufacturer and www.rockstargames.com/support for current compatibility information. Some system components such as mobile chipsets, integrated, and AGP graphics cards may be incompatible. Unlisted specifications may not be supported by publisher.     Other requirements:  Installation and online play requires log-in to Rockstar Games Social Club (13+) network; internet connection required for activation, online play, and periodic entitlement verification; software installations required including Rockstar Games Social Club platform, DirectX , Chromium, and Microsoft Visual C++ 2008 sp1 Redistributable Package, and authentication software that recognizes certain hardware attributes for entitlement, digital rights management, system, and other support purposes.     SINGLE USE SERIAL CODE REGISTRATION VIA INTERNET REQUIRED; REGISTRATION IS LIMITED TO ONE ROCKSTAR GAMES SOCIAL CLUB ACCOUNT (13+) PER SERIAL CODE; ONLY ONE PC LOG-IN ALLOWED PER SOCIAL CLUB ACCOUNT AT ANY TIME; SERIAL CODE(S) ARE NON-TRANSFERABLE ONCE USED; SOCIAL CLUB ACCOUNTS ARE NON-TRANSFERABLE.  Partner Requirements:  Please check the terms of service of this site before purchasing this software.",
                        recommended = "Recommended:OS: Windows 10 64 Bit, Windows 8.1 64 Bit, Windows 8 64 Bit, Windows 7 64 Bit Service Pack 1Processor: Intel Core i5 3470 @ 3.2GHz (4 CPUs) / AMD X8 FX-8350 @ 4GHz (8 CPUs)Memory: 8 GB RAMGraphics: NVIDIA GTX 660 2GB / AMD HD 7870 2GBStorage: 72 GB available spaceSound Card: 100% DirectX 10 compatibleAdditional Notes:",
                    )
                ),
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 16,
                        name = "PlayStation 3",
                        slug = "playstation3",
                        image = null,
                        year_end = null,
                        year_start = null,
                        games_count = 3467,
                        image_background = "https://media.rawg.io/media/games/fc1/fc1307a2774506b5bd65d7e8424664a7.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = null
                ),
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 14,
                        name = "Xbox 360",
                        slug = "xbox360",
                        image = null,
                        year_end = null,
                        year_start = null,
                        games_count = 2772,
                        image_background = "https://media.rawg.io/media/games/d69/d69810315bd7e226ea2d21f9156af629.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = null
                ),
                GamePlatformApiModel(
                    platform = PlatformApiModel(
                        id = 1,
                        name = "Xbox One",
                        slug = "xbox-one",
                        image = null,
                        year_end = null,
                        year_start = null,
                        games_count = 5461,
                        image_background = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
                    ),
                    released_at = "2013-09-17",
                    requirements = null
                ),
            ),
            stores = listOf<StoreResponse>(
                StoreResponse(
                    id = 290375,
                    url = "",
                    store = StoreApiModel(
                        id = 3,
                        name = "PlayStation Store",
                        slug = "playstation-store",
                        domain = "store.playstation.com",
                        games_count = 7786,
                        image_background = "https://media.rawg.io/media/games/f87/f87457e8347484033cb34cde6101d08d.jpg",
                    )
                ),
                StoreResponse(
                    id = 438095,
                    url = "",
                    store = StoreApiModel(
                        id = 11,
                        name = "Epic Games",
                        slug = "epic-games",
                        domain = "epicgames.com",
                        games_count = 1190,
                        image_background = "https://media.rawg.io/media/games/c80/c80bcf321da44d69b18a06c04d942662.jpg",
                    )
                ),
                StoreResponse(
                    id = 290376,
                    url = "",
                    store = StoreApiModel(
                        id = 1,
                        name = "Steam",
                        slug = "steam",
                        domain = "store.steampowered.com",
                        games_count = 70600,
                        image_background = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
                    )
                ),
                StoreResponse(
                    id = 290377,
                    url = "",
                    store = StoreApiModel(
                        id = 7,
                        name = "Xbox 360 Store",
                        slug = "xbox360",
                        domain = "marketplace.xbox.com",
                        games_count = 1911,
                        image_background = "https://media.rawg.io/media/games/120/1201a40e4364557b124392ee50317b99.jpg",
                    )
                ),
                StoreResponse(
                    id = 290378,
                    url = "",
                    store = StoreApiModel(
                        id = 2,
                        name = "Xbox Store",
                        slug = "xbox-store",
                        domain = "microsoft.com",
                        games_count = 4755,
                        image_background = "https://media.rawg.io/media/games/4a0/4a0a1316102366260e6f38fd2a9cfdce.jpg",
                    )
                ),
            ),
            developers = listOf<DeveloperApiModel>(
                DeveloperApiModel(
                    id = 3524,
                    name = "Rockstar North",
                    slug = "rockstar-north",
                    games_count = 29,
                    image_background = "https://media.rawg.io/media/screenshots/b98/b98adb52b2123a14d1c88e828a6b49f3.jpg",
                ),
                DeveloperApiModel(
                    id = 10,
                    name = "Rockstar Games",
                    slug = "rockstar-games",
                    games_count = 25,
                    image_background = "https://media.rawg.io/media/games/686/686909717c3aa01518bc42ae2bf4259e.jpg",
                ),
            ),
            genres = listOf<GenreApiModel>(
                GenreApiModel(
                    id = 4,
                    name = "Action",
                    slug = "action",
                    games_count = 170590,
                    image_background = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
                ),
                GenreApiModel(
                    id = 3,
                    name = "Adventure",
                    slug = "adventure",
                    games_count = 130714,
                    image_background = "https://media.rawg.io/media/games/960/960b601d9541cec776c5fa42a00bf6c4.jpg",
                ),
            ),
            tags = listOf<TagApiModel>(
                TagApiModel(
                    id = 31,
                    name = "Singleplayer",
                    slug = "singleplayer",
                    language = "eng",
                    games_count = 197445,
                    image_background = "https://media.rawg.io/media/games/b8c/b8c243eaa0fbac8115e0cdccac3f91dc.jpg",
                ),
                TagApiModel(
                    id = 40847,
                    name = "Steam Achievements",
                    slug = "steam-achievements",
                    language = "eng",
                    games_count = 28557,
                    image_background = "https://media.rawg.io/media/games/4be/4be6a6ad0364751a96229c56bf69be59.jpg",
                ),
                TagApiModel(
                    id = 7,
                    name = "Multiplayer",
                    slug = "multiplayer",
                    language = "eng",
                    games_count = 34459,
                    image_background = "https://media.rawg.io/media/games/34b/34b1f1850a1c06fd971bc6ab3ac0ce0e.jpg",
                ),
                TagApiModel(
                    id = 13,
                    name = "Atmospheric",
                    slug = "atmospheric",
                    language = "eng",
                    games_count = 27338,
                    image_background = "https://media.rawg.io/media/games/328/3283617cb7d75d67257fc58339188742.jpg",
                ),
                TagApiModel(
                    id = 40836,
                    name = "Full controller support",
                    slug = "full controller support",
                    language = "eng",
                    games_count = 13411,
                    image_background = "https://media.rawg.io/media/games/f46/f466571d536f2e3ea9e815ad17177501.jpg",
                ),
                TagApiModel(
                    id = 42,
                    name = "Great Soundtrack",
                    slug = "great-soundtrack",
                    language = "eng",
                    games_count = 3221,
                    image_background = "https://media.rawg.io/media/games/4cf/4cfc6b7f1850590a4634b08bfab308ab.jpg",
                ),
                TagApiModel(
                    id = 24,
                    name = "RPG",
                    slug = "rpg",
                    language = "eng",
                    games_count = 16160,
                    image_background = "https://media.rawg.io/media/games/d69/d69810315bd7e226ea2d21f9156af629.jpg",
                ),
                TagApiModel(
                    id = 18,
                    name = "Co-op",
                    slug = "co-op",
                    language = "eng",
                    games_count = 9377,
                    image_background = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                ),
                TagApiModel(
                    id = 36,
                    name = "Open World",
                    slug = "open-world",
                    language = "eng",
                    games_count = 5908,
                    image_background = "https://media.rawg.io/media/games/d82/d82990b9c67ba0d2d09d4e6fa88885a7.jpg",
                ),
                TagApiModel(
                    id = 411,
                    name = "cooperative",
                    slug = "cooperative",
                    language = "eng",
                    games_count = 3797,
                    image_background = "https://media.rawg.io/media/games/4e0/4e0e7b6d6906a131307c94266e5c9a1c.jpg",
                ),
                TagApiModel(
                    id = 8,
                    name = "First-Person",
                    slug = "first-person",
                    language = "eng",
                    games_count = 27075,
                    image_background = "https://media.rawg.io/media/games/b7b/b7b8381707152afc7d91f5d95de70e39.jpg",
                ),
                TagApiModel(
                    id = 149,
                    name = "Third Person",
                    slug = "third-person",
                    language = "eng",
                    games_count = 8686,
                    image_background = "https://media.rawg.io/media/games/b45/b45575f34285f2c4479c9a5f719d972e.jpg",
                ),
                TagApiModel(
                    id = 4,
                    name = "Funny",
                    slug = "funny",
                    language = "eng",
                    games_count = 21813,
                    image_background = "https://media.rawg.io/media/games/a3c/a3c529a12c896c0ef02db5b4741de2ba.jpg",
                ),
                TagApiModel(
                    id = 37,
                    name = "Sandbox",
                    slug = "sandbox",
                    language = "eng",
                    games_count = 5660,
                    image_background = "https://media.rawg.io/media/games/d7d/d7d33daa1892e2468cd0263d5dfc957e.jpg",
                ),
                TagApiModel(
                    id = 123,
                    name = "Comedy",
                    slug = "comedy",
                    language = "eng",
                    games_count = 10378,
                    image_background = "https://media.rawg.io/media/games/806/8060a7663364ac23e15480728938d6f3.jpg",
                ),
                TagApiModel(
                    id = 150,
                    name = "Third-Person Shooter",
                    slug = "third-person-shooter",
                    language = "eng",
                    games_count = 2730,
                    image_background = "https://media.rawg.io/media/games/5c0/5c0dd63002cb23f804aab327d40ef119.jpg",
                ),
                TagApiModel(
                    id = 62,
                    name = "Moddable",
                    slug = "moddable",
                    language = "eng",
                    games_count = 753,
                    image_background = "https://media.rawg.io/media/games/7cf/7cfc9220b401b7a300e409e539c9afd5.jpg",
                ),
                TagApiModel(
                    id = 144,
                    name = "Crime",
                    slug = "crime",
                    language = "eng",
                    games_count = 2445,
                    image_background = "https://media.rawg.io/media/games/473/473bd9a5e9522629d6cb28b701fb836a.jpg",
                ),
                TagApiModel(
                    id = 62349,
                    name = "vr mod",
                    slug = "vr-mod",
                    language = "eng",
                    games_count = 17,
                    image_background = "https://media.rawg.io/media/screenshots/1bb/1bb3f78f0fe43b5d5ca2f3da5b638840.jpg",
                ),
            ),
            publishers = listOf<PublisherApiModel>(
                PublisherApiModel(
                    id = 2155,
                    name = "Rockstar Games",
                    slug = "rockstar-games",
                    games_count = 76,
                    image_background = "https://media.rawg.io/media/games/c23/c23b578d6a9dd1c73ee403157184e793.jpg",
                ),
            ),
        )

        val gameDetails = gameDetailsApi.toModel()
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

        return Resource.success(res)
    }

    override suspend fun getGameStores(
        id: Int,
        remoteStores: List<StoreModel>
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

        //Полученный список магазинов из детальной информации об игре
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

        val res: List<GameToStoreModel> = gameStores.map {
            val rStore = fakeRemoteStores.find { rStore ->
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

        return Resource.success(res)
    }

    override suspend fun getGameScreenshots(id: Int): Resource<List<ScreenshotModel>> {
        val apiScreenshots = listOf<ScreenshotApiModel>(
            ScreenshotApiModel(
                id = 1827221,
                image = "https://media.rawg.io/media/screenshots/a7c/a7c43871a54bed6573a6a429451564ef.jpg",
                width = 1920,
                height = 1080,
                is_deleted = false,
            ),
            ScreenshotApiModel(
                id = 1827222,
                image = "https://media.rawg.io/media/screenshots/cf4/cf4367daf6a1e33684bf19adb02d16d6.jpg",
                width = 1920,
                height = 1080,
                is_deleted = false,
            ),
            ScreenshotApiModel(
                id = 1827223,
                image = "https://media.rawg.io/media/screenshots/f95/f9518b1d99210c0cae21fc09e95b4e31.jpg",
                width = 1920,
                height = 1080,
                is_deleted = false,
            ),
            ScreenshotApiModel(
                id = 1827225,
                image = "https://media.rawg.io/media/screenshots/a5c/a5c95ea539c87d5f538763e16e18fb99.jpg",
                width = 1920,
                height = 1080,
                is_deleted = false,
            ),
            ScreenshotApiModel(
                id = 1827226,
                image = "https://media.rawg.io/media/screenshots/a7e/a7e990bc574f4d34e03b5926361d1ee7.jpg",
                width = 1920,
                height = 1080,
                is_deleted = false,
            ),
            ScreenshotApiModel(
                id = 1827227,
                image = "https://media.rawg.io/media/screenshots/592/592e2501d8734b802b2a34fee2df59fa.jpg",
                width = 1920,
                height = 1080,
                is_deleted = false,
            ),
        )

        val domainScreenshots = apiScreenshots.map { it.toModel() }

        delay(1000)
        return Resource.success(domainScreenshots)
    }
}