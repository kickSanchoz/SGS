package ru.sanchozgamesstore.android.utils.itemDecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.utils.px

/**
 * @param insetBetween Отступы между элементами ресайклера
 * @param insetLeft Внешний отступ ресайклера слева
 * @param insetTop Внешний отступ ресайклера сверху
 * @param insetRight Внешний отступ ресайклера справа
 * @param insetBottom Внешний отступ ресайклера снизу
 * */
class SidesItemDecoration(
    context: Context,
    insetBetween: Int = 0,
    insetLeft: Int = 0,
    insetTop: Int = 0,
    insetRight: Int = 0,
    insetBottom: Int = 0,
) : RecyclerView.ItemDecoration() {

    /** Отступы между элементами ресайклера */
    private val insetBetweenDp = insetBetween.px

    /** Внешний отступ ресайклера слева */
    private val insetLeftDp = insetLeft.px

    /** Внешний отступ ресайклера сверху */
    private val insetTopDp = insetTop.px

    /** Внешний отступ ресайклера справа */
    private val insetRightDp = insetRight.px

    /** Внешний отступ ресайклера снизу */
    private val insetBottomDp = insetBottom.px

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager
        if (layoutManager is LinearLayoutManager) {
            val orientation = layoutManager.orientation

            if (orientation == LinearLayout.VERTICAL) { //vertical recycler
                //У всех элементов сверху устанавливается отступ равный отступу между элементами
                outRect.top = insetBetweenDp
                outRect.left = insetLeftDp
                outRect.right = insetRightDp

                // Добавить сдвиг только для первого элемента, чтобы избежать двойного смещения между айтемами
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = insetTopDp
                } else if (parent.getChildAdapterPosition(view) == state.itemCount.minus(1)) {
                    outRect.bottom = insetBottomDp
                }
            } else { //horizontal recycler
                //У всех элементов слева устанавливается отступ равный отступу между элементами
                outRect.left = insetBetweenDp
                outRect.top = insetTopDp
                outRect.bottom = insetBottomDp

                // Добавить сдвиг только для первого элемента, чтобы избежать двойного смещения между айтемами
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = insetLeftDp
                } else if (parent.getChildAdapterPosition(view) == state.itemCount.minus(1)) {
                    outRect.right = insetRightDp
                }
            }
        } else {
            throw IllegalStateException("You probable forgot set the layout manager or chose not LinearLayoutManager")
        }
    }
}