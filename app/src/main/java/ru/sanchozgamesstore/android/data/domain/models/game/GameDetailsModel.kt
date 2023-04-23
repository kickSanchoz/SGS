package ru.sanchozgamesstore.android.data.domain.models.game

import ru.sanchozgamesstore.android.data.domain.models.developer.DeveloperModel
import ru.sanchozgamesstore.android.data.domain.models.genre.GenreModel
import ru.sanchozgamesstore.android.data.domain.models.platform.MetacriticPlatformModel
import ru.sanchozgamesstore.android.data.domain.models.platform.ParentPlatformModel
import ru.sanchozgamesstore.android.data.domain.models.publisher.PublisherModel
import ru.sanchozgamesstore.android.data.domain.models.rating.RatingMap
import ru.sanchozgamesstore.android.data.domain.models.rating.RatingModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.models.tag.TagModel
import ru.sanchozgamesstore.android.utils.converter.DateConverter

/**
 * Доменовская модель. Детальная информация по игре
 *
 * @param id id игры
 * @param name название игры
 * @param name_original оригинальное название игры
 * @param slug lowercase, no-space название игры
 * @param description описание игры с html тэгами
 * @param description_raw описание игры без html тэгов
 * @param metacritic средняя оценка игры на metacritic по всем платформам
 * @param metacritic_platforms список с оценками игры на metacritic по каждой отдельной платформе
 * @param _released дата релиза игры (YYYY-MM-DD)
 * @param tba информация об игре будет оглашена позже
 * @param _updated дата последнего обновления информации об игре (YYYY-MM-DDTHH:MM:SS)
 * @param _background_image картинка-фон игры
 * @param _background_image_additional дополнительная картинка-фон игры
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
data class GameDetailsModel(
    val id: Int,
    val name: String,
    val name_original: String?,
    val slug: String,
    val description: String?,
    val description_raw: String?,
    val metacritic: Int,
    val metacritic_platforms: List<MetacriticPlatformModel>,
    private val _released: String?,
    val tba: Boolean,
    private val _updated: String?,
    private val _background_image: String?,
    private val _background_image_additional: String?,
    val website: String?,
    val rating: Double,
    val rating_top: Int?,
    val ratings: List<RatingModel>,
    val playtime: Int?,
    val screenshots_count: Int?,
    val movies_count: Int?,
    val achievements_count: Int?,
    val parent_achievements_count: Int?,
    val reviews_text_count: Int?,
    val ratings_count: Int?,
    val reviews_count: Int?,
    val saturated_color: String?,
    val dominant_color: String?,
    val parent_platforms: List<ParentPlatformModel>,
    val platforms: List<GamePlatformModel>,
    val stores: List<StoreModel>,
    val developers: List<DeveloperModel>,
    val genres: List<GenreModel>,
    val tags: List<TagModel>,
    val publishers: List<PublisherModel>,
) {
    /**
     * Дата релиза игры (dd.MM.yyyy)
     * */
    val released: String?
        get() = DateConverter.backendDateToDisplayDate(_released).takeIf { _released != null }

    /**
     * Дата последнего обновления информации об игре (dd.MM.yyyy HH:mm)
     * */
    val updated: String
        get() = DateConverter.backendDateTimeToDisplayDateTime(_updated)

    val background_image: String?
        get() = _background_image ?: _background_image_additional

    val background_image_additional: String?
        get() = _background_image_additional

    /**
     * Мапа "разновидность рейтинга" - "данные рейтинга"
     * */
    val ratingMap: RatingMap
        get() = RatingMap(displayAllKnownRatings = true).apply {
            setRatings(ratings)
        }
}


