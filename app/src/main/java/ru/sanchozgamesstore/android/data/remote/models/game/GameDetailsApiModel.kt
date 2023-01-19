package ru.sanchozgamesstore.android.data.remote.models.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.remote.models.developer.DeveloperApiModel
import ru.sanchozgamesstore.android.data.remote.models.genre.GenreApiModel
import ru.sanchozgamesstore.android.data.remote.models.platform.MetacriticPlatformApiModel
import ru.sanchozgamesstore.android.data.remote.models.platform.ParentPlatformResponse
import ru.sanchozgamesstore.android.data.remote.models.publisher.PublisherApiModel
import ru.sanchozgamesstore.android.data.remote.models.rating.RatingApiModel
import ru.sanchozgamesstore.android.data.remote.models.store.StoreResponse
import ru.sanchozgamesstore.android.data.remote.models.tag.TagApiModel

/**
 * Серверная модель. Детальная информация по игре
 *
 * @param id id игры
 * @param name название игры
 * @param name_original оригинальное название игры
 * @param slug lowercase, no-space название игры
 * @param description описание игры с html тэгами
 * @param description_raw описание игры без html тэгов
 * @param metacritic средняя оценка игры на metacritic по всем платформам
 * @param metacritic_platforms список с оценками игры на metacritic по каждой отдельной платформе
 * @param released дата релиза игры (YYYY-MM-DD)
 * @param tba информация об игре будет оглашена позже
 * @param updated дата последнего обновления информации об игре (YYYY-MM-DDTHH:MM:SS)
 * @param background_image картинка-фон игры
 * @param background_image_additional дополнительная картинка-фон игры
 * @param website url сайта игры
 * @param rating средний рейтинг игры
 * @param rating_top высшая оценка игры
 * @param ratings список рейтингов с процентным и количественным соотношением для каждой оценки
 * @param playtime среднее время людей, проведенное в игре (в часах)
 * @param screenshots_count кол-во скриншотов
 * @param movies_count кол-во трейлеров
 * @param achievements_count кол-во достижений (?)
 * @param parent_achievements_count кол-во достижений в игре
 * @param reviews_text_count кол-во обзоров, оставленных критиками
 * @param ratings_count кол-во оставленных оценок
 * @param reviews_count кол-во обзоров оставленных людьми
 * @param saturated_color насыщенный цвет
 * @param dominant_color основной цвет
 * @param parent_platforms список платформ БЕЗ указания конкретной модели, на которых выпущена игра
 * @param platforms список платформ С указанием конкретной модели, на которых выпущена игра
 * @param stores список магазинов, на которых продаётся игра
 * @param developers список разработчиков игры
 * @param genres список жанров игры
 * @param tags список тэгов игры
 * @param publishers список издателей игры
 * */
@JsonClass(generateAdapter = true)
data class GameDetailsApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = NAME) val name: String,
    @Json(name = NAME_ORIGINAL) val name_original: String,
    @Json(name = SLUG) val slug: String,
    @Json(name = DESCRIPTION) val description: String,
    @Json(name = DESCRIPTION_RAW) val description_raw: String,
    @Json(name = METACRITIC) val metacritic: Int,
    @Json(name = METACRITIC_PLATFORMS) val metacritic_platforms: List<MetacriticPlatformApiModel>,
    @Json(name = RELEASED) val released: String,
    @Json(name = TBA) val tba: Boolean,
    @Json(name = UPDATED) val updated: String,
    @Json(name = BACKGROUND_IMAGE) val background_image: String,
    @Json(name = BACKGROUND_IMAGE_ADDITIONAL) val background_image_additional: String,
    @Json(name = WEBSITE) val website: String,
    @Json(name = RATING) val rating: Double,
    @Json(name = RATING_TOP) val rating_top: Int,
    @Json(name = RATINGS) val ratings: List<RatingApiModel>,
    @Json(name = PLAYTIME) val playtime: Int,
    @Json(name = SCREENSHOTS_COUNT) val screenshots_count: Int,
    @Json(name = MOVIES_COUNT) val movies_count: Int,
    @Json(name = ACHIEVEMENTS_COUNT) val achievements_count: Int,
    @Json(name = PARENT_ACHIEVEMENTS_COUNT) val parent_achievements_count: Int,
    @Json(name = REVIEWS_TEXT_COUNT) val reviews_text_count: Int,
    @Json(name = RATINGS_COUNT) val ratings_count: Int,
    @Json(name = REVIEWS_COUNT) val reviews_count: Int,
    @Json(name = SATURATED_COLOR) val saturated_color: String,
    @Json(name = DOMINANT_COLOR) val dominant_color: String,
    @Json(name = PARENT_PLATFORMS) val parent_platforms: List<ParentPlatformResponse>,
    @Json(name = PLATFORMS) val platforms: List<GamePlatformApiModel>,
    @Json(name = STORES) val stores: List<StoreResponse>,
    @Json(name = DEVELOPERS) val developers: List<DeveloperApiModel>,
    @Json(name = GENRES) val genres: List<GenreApiModel>,
    @Json(name = TAGS) val tags: List<TagApiModel>,
    @Json(name = PUBLISHERS) val publishers: List<PublisherApiModel>,
) {
    fun toModel(): GameDetailsModel {
        return GameDetailsModel(
            id = id,
            name = name,
            name_original = name_original,
            slug = slug,
            description = description,
            description_raw = description_raw,
            metacritic = metacritic,
            metacritic_platforms = metacritic_platforms.map {
                it.toModel()
            },
            _released = released,
            tba = tba,
            _updated = updated,
            background_image = background_image,
            background_image_additional = background_image_additional,
            website = website,
            rating = rating,
            rating_top = rating_top,
            ratings = ratings.map { it.toModel() },
            playtime = playtime,
            screenshots_count = screenshots_count,
            movies_count = movies_count,
            achievements_count = achievements_count,
            parent_achievements_count = parent_achievements_count,
            reviews_text_count = reviews_text_count,
            ratings_count = ratings_count,
            reviews_count = reviews_count,
            saturated_color = saturated_color,
            dominant_color = dominant_color,
            parent_platforms = parent_platforms.map {
                it.platform.toModel()
            },
            platforms = platforms.map {
                it.toModel()
            },
            stores = stores.map {
                it.store.toModel()
            },
            developers = developers.map {
                it.toModel()
            },
            genres = genres.map {
                it.toModel()
            },
            tags = tags.map {
                it.toModel()
            },
            publishers = publishers.map {
                it.toModel()
            },
        )
    }

    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val NAME_ORIGINAL = "name_original"
        const val SLUG = "slug"
        const val DESCRIPTION = "description"
        const val DESCRIPTION_RAW = "description_raw"
        const val METACRITIC = "metacritic"
        const val METACRITIC_PLATFORMS = "metacritic_platforms"
        const val RELEASED = "released"
        const val TBA = "tba"
        const val UPDATED = "updated"
        const val BACKGROUND_IMAGE = "background_image"
        const val BACKGROUND_IMAGE_ADDITIONAL = "background_image_additional"
        const val WEBSITE = "website"
        const val RATING = "rating"
        const val RATING_TOP = "rating_top"
        const val RATINGS = "ratings"
        const val PLAYTIME = "playtime"
        const val SCREENSHOTS_COUNT = "screenshots_count"
        const val MOVIES_COUNT = "movies_count"
        const val ACHIEVEMENTS_COUNT = "achievements_count"
        const val PARENT_ACHIEVEMENTS_COUNT = "parent_achievements_count"
        const val REVIEWS_TEXT_COUNT = "reviews_text_count"
        const val RATINGS_COUNT = "ratings_count"
        const val REVIEWS_COUNT = "reviews_count"
        const val SATURATED_COLOR = "saturated_color"
        const val DOMINANT_COLOR = "dominant_color"
        const val PARENT_PLATFORMS = "parent_platforms"
        const val PLATFORMS = "platforms"
        const val STORES = "stores"
        const val DEVELOPERS = "developers"
        const val GENRES = "genres"
        const val TAGS = "tags"
        const val PUBLISHERS = "publishers"
    }
}
