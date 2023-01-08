package ru.sanchozgamesstore.android.data.domain.models.rating

/**
 * Доменоская модель. Каждая отдельная оценка игры
 *
 * @param id id типа рейтинга
 * @param _title название рейтинга
 * @param _count кол-во оценок
 * @param _percent процент оценок данного типа
 * */
data class RatingModel(
    val id: Int,
    val _title: String?,
    val _count: Int?,
    val _percent: Double?,
) {
    val title: String
        get() = _title ?: "Unknown"
    val count: Int
        get() = _count ?: 0
    val percent: Double
        get() = _percent ?: 0.0
}
