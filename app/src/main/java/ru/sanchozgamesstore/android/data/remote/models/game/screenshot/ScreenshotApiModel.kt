package ru.sanchozgamesstore.android.data.remote.models.game.screenshot

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel

/**
 * Серверная модель. Скриншоты игры
 *
 * @param id id скриншота
 * @param image url скриншота
 * @param width ширина скриншота
 * @param height высота скриншота
 * @param is_deleted (?)
 * */
@JsonClass(generateAdapter = true)
data class ScreenshotApiModel(
    @Json(name = ID) val id: Int,
    @Json(name = IMAGE) val image: String,
    @Json(name = WIDTH) val width: Int,
    @Json(name = HEIGHT) val height: Int,
    @Json(name = IS_DELETED) val is_deleted: Boolean,
) {
    fun toModel(): ScreenshotModel {
        return ScreenshotModel(
            id = id,
            image = image,
            width = width,
            height = height,
            is_deleted = is_deleted,
        )
    }

    companion object {
        const val ID = "id"
        const val IMAGE = "image"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val IS_DELETED = "is_deleted"
    }
}

