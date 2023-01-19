package ru.sanchozgamesstore.android.data.domain.models.game.screnshot

data class ScreenshotModel(
    val id: Int,
    val image: String,
    val width: Int,
    val height: Int,
    val is_deleted: Boolean,
)
