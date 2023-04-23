package ru.sanchozgamesstore.android.data.remote.models.game.screenshot

import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse
import ru.sanchozgamesstore.android.data.remote.models.game.GameToStoreBriefApiModel

data class GameToStoreBriefResponse(
    override var results: List<GameToStoreBriefApiModel>?
) : PaginationResponse<GameToStoreBriefApiModel>()
