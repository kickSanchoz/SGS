package ru.sanchozgamesstore.android.data.remote.models.game

import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse

@JsonClass(generateAdapter = true)
data class FavoriteGamesResponse(
    override var results: List<GameDetailsApiModel>?
) : PaginationResponse<GameDetailsApiModel>() {

}