package ru.sanchozgamesstore.android.data.domain.models.rating

import ru.sanchozgamesstore.android.data.domain.enums.RatingSpecies
import java.util.*

class RatingMap(
    displayAllKnownRatings: Boolean = true
) : Map<RatingSpecies, RatingModel> {

    /**
     * Мапа "разновидность рейтинга" - "модель рейтинга"
     *
     * (ключами являются все значения RatingSpecies, которые существуют в переменной изначально)
     * */
    private val ratings: EnumMap<RatingSpecies, RatingModel> = EnumMap(RatingSpecies::class.java)

    init {
        if (displayAllKnownRatings) {
            //Для каждого ключа уставливается начальное значение рейтинга
            RatingSpecies.values().map {
                ratings[it] = RatingModel(
                    id = it.id,
                    _title = it.title,
                    _count = null,
                    _percent = null,
                )
            }
        }
    }

    /**
     * Установить рейтинг для каждого известного ключа
     *
     * @param ratingList список рейтингов
     * */
    fun setRatings(ratingList: List<RatingModel>) {
        ratingList.forEach { r ->
            val key = RatingSpecies.getRatingSpeciesById(r.id)

            //Если ключ UNDEFINED, то складывать все рейтинги, которые неизвестные
            if (key == RatingSpecies.UNDEFINED) {
                /*
                * Если элемент с ключом UNDEFINED уже был установлен, то добавить данные к нему,
                * иначе создать этот элемент
                * */
                ratings[key]?.plus(r) ?: run { ratings[key] = r }
                return@forEach
            }

            /*
            * Если ключ соответствует одному из известных приложению, то присвоить рейтинг к
            * этому ключу
            * */
            ratings[key] = r
        }
    }

    //-----------------------------Переопределенные методы-----------------------------

    override val entries: Set<Map.Entry<RatingSpecies, RatingModel>>
        get() = ratings.entries
    override val keys: Set<RatingSpecies>
        get() = ratings.keys
    override val size: Int
        get() = ratings.size
    override val values: Collection<RatingModel>
        get() = ratings.values

    override fun containsKey(key: RatingSpecies): Boolean {
        return ratings.containsKey(key)
    }

    override fun containsValue(value: RatingModel): Boolean {
        return ratings.containsValue(value)
    }

    override fun get(key: RatingSpecies): RatingModel? {
        return ratings[key]
    }

    override fun isEmpty(): Boolean {
        return ratings.isEmpty()
    }

    //-----------------------------Переопределенные методы-----------------------------

}