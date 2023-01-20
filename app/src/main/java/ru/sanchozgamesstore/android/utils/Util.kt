package ru.sanchozgamesstore.android.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IntegerRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.request.ImageRequest
import ru.sanchozgamesstore.R

/**
 * Переводит указанное количество px в dp
 * */
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * Переводит указанное количество dp в px
 * */
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun getPlaceholder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        setStyle(CircularProgressDrawable.DEFAULT)
        setColorSchemeColors(ResourcesCompat.getColor(context.resources, R.color.red, null))
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    view.clearFocus()
}

/**
 * Получить цвет в зависимости от рейтинга
 * */
@IntegerRes
fun getColorByRatingScore(
    @androidx.annotation.IntRange(from = 0, to = 100) rating: Int
): Int {
    return when (rating) {
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
            R.color.white
        }
    }
}

/**
 * Удалить все декораторы ресайклера
 * */
fun RecyclerView.removeItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}

fun ImageRequest.Builder.defaultPictureLoadParams(
    context: Context
): Unit {
    crossfade(true)
    placeholder(getPlaceholder(context))
    error(R.drawable.ic_question_mark)
}