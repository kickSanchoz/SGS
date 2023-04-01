package ru.sanchozgamesstore.android.utils

import androidx.annotation.ColorRes
import ru.sanchozgamesstore.R

/**
 * Цвет в зависимости от рейтинга
 * */
@ColorRes
fun getColorByScore(score: Int): Int {
    return when (score) {
        in 0..50 -> {
            R.color.red
        }
        in 51..79 -> {
            R.color.yellow
        }
        in 80..100 -> {
            R.color.green
        }
        else -> {
            R.color.empty_grey
        }
    }
}