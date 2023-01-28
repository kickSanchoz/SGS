package ru.sanchozgamesstore.android.data.domain.models.game.screenshot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Доменоская модель. Скриншоты игры
 *
 * @param id id скриншота
 * @param image url скриншота
 * @param width ширина скриншота
 * @param height высота скриншота
 * @param is_deleted (?)
 * */
@Parcelize
data class ScreenshotModel(
    val id: Int,
    val image: String,
    val width: Int,
    val height: Int,
    val is_deleted: Boolean,
) : Parcelable
