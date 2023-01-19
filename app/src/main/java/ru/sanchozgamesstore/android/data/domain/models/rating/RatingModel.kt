package ru.sanchozgamesstore.android.data.domain.models.rating

import ru.sanchozgamesstore.android.data.domain.enums.RatingSpecies

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
    private var _title: String?,
    private var _count: Int?,
    private var _percent: Double?,
) : Comparable<RatingModel> {
    val title: String
        get() = _title?.replaceFirstChar { it.uppercase() } ?: RatingSpecies.DEFAULT_TITLE
    val count: Int
        get() = _count ?: 0
    val percent: Double
        get() = _percent ?: 0.0

    operator fun plus(other: RatingModel) {
        this._title = RatingSpecies.DEFAULT_TITLE
        this._count = this.count + other.count
        this._percent = this.percent + other.percent
    }

    /**
     * Сравниваем рейтинги по их процентному соотношею
     *
     * @return
     * 1 - данная модель больше сравниваемой
     *
     * 0 - модели равны
     *
     * -1 - данная модель меньше сравниваемой
     * */
    override fun compareTo(other: RatingModel): Int {
        return when {
            this.percent > other.percent -> {
                1
            }
            this.percent == other.percent -> {
                0
            }
            else -> {
                -1
            }
        }
    }
}
