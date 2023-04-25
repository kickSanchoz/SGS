package ru.sanchozgamesstore.android.data.remote.models.game

import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse

data class GamesResponse(
    override var results: List<GameDetailsApiModel>?
) : PaginationResponse<GameDetailsApiModel>()