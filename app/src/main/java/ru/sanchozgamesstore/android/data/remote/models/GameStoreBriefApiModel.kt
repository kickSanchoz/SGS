package ru.sanchozgamesstore.android.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.GameStoreBriefModel

/**
 * @param id id связной таблицы
 * @param game_id id игры
 * @param store_id id магазина
 * @param url страница игры в интернет магазине
 * */
@JsonClass(generateAdapter = true)
data class GameStoreBriefApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = GAME_ID) val game_id: Int,
    @Json(name = STORE_ID) val store_id: Int,
    @Json(name = URL) val url: String
) {
    fun toModel(): GameStoreBriefModel {
        return GameStoreBriefModel(
            id = id,
            game_id = game_id,
            store_id = store_id,
            url = url
        )
    }

    companion object {
        const val ID = "id"
        const val GAME_ID = "game_id"
        const val STORE_ID = "store_id"
        const val URL = "url"
    }
}
