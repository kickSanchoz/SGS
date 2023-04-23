package ru.sanchozgamesstore.android.data.remote.models.game.screenshot

import ru.sanchozgamesstore.android.data.remote.models.PaginationResponse

data class ScreenshotsResponse(
    override var results: List<ScreenshotApiModel>?
) : PaginationResponse<ScreenshotApiModel>()