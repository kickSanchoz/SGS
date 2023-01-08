package ru.sanchozgamesstore.android.data.remote.models.rating

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.rating.RatingModel

/**
 * Серверная модель. Каждая отдельная оценка игры
 *
 * @param id id типа рейтинга
 * @param title название рейтинга
 * @param count кол-во оценок
 * @param percent процент оценок данного типа
 * */
@JsonClass(generateAdapter = true)
data class RatingApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = TITLE) val title: String,
    @Json(name = COUNT) val count: Int,
    @Json(name = PERCENT) val percent: Double,
) {
    fun toModel(): RatingModel {
        return RatingModel(
            id = id,
            _title = title,
            _count = count,
            _percent = percent,
        )
    }

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val COUNT = "count"
        const val PERCENT = "percent"
    }
}