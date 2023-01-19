package ru.sanchozgamesstore.android.utils.itemDecoration

import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.utils.px

/**
 * @param insetBetween Отступ между элементами ресайклера
 * @param insetOut Отступы для ресайклера снаружи:
 *
 * для горизонтального лейаута - слева, справа;
 *
 * для вертикального лейаута - сверху, снизу.
 * @param insetSides Доп. отступы для ресайлера по сторонам:
 *
 * для горизонтального лейаута - сверху, снизу;
 *
 * для вертикального лейаута - слева, справа.
 * */
class OrientationItemDecoration(
    insetBetween: Int = 0,
    insetOut: Int = 0,
    insetSides: Int = 0
) : RecyclerView.ItemDecoration() {

    /** Отступ между элементами ресайклера */
    private val insetBetweenDp = insetBetween.px

    /** Отступы для ресайклера снаружи:
     *
     * для горизонтального лейаута - слева, справа;
     *
     * для вертикального лейаута - сверху, снизу.
     * */
    /*
    *  |      ^^       |    |                                 |
    *  | ############# |    |                                 |
    *  | #           # |    |                                 |
    *  | #           # |    |   ###########################   |
    *  | #           # |    |   #                         #   |
    *  | #           # |    | < #                         # > |
    *  | #           # |    | < #                         # > |
    *  | #           # |    |   #                         #   |
    *  | #           # |    |   ###########################   |
    *  | #           # |    |                                 |
    *  | ############# |    |                                 |
    *  |      vv       |    |                                 |
    * */
    private val insetOutDp = insetOut.px

    /** Доп. отступы для ресайлера по сторонам:
     *
     * для горизонтального лейаута - сверху, снизу;
     *
     * для вертикального лейаута - слева, справа.
     * */
    /*
    *  |                 |     |                             |
    *  |  #############  |     |                             |
    *  |  #           #  |     |              ^^             |
    *  |  #           #  |     | ########################### |
    *  |  #           #  |     | #                         # |
    *  |< #           # >|     | #                         # |
    *  |< #           # >|     | #                         # |
    *  |  #           #  |     | #                         # |
    *  |  #           #  |     | ########################### |
    *  |  #           #  |     |              vv             |
    *  |  #############  |     |                             |
    *  |                 |     |                             |
    * */
    private val insetSidesDp = insetSides.px

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
                outRect.left = insetSidesDp
                outRect.right = insetSidesDp

                // Добавить сдвиг только для первого элемента, чтобы избежать двойного смещения между айтемами
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = insetOutDp
                } else if (parent.getChildAdapterPosition(view) == state.itemCount.minus(1)) {
                    outRect.bottom = insetOutDp
                }
            } else {  //horizontal recycler
                //У всех элементов слева устанавливается отступ равный отступу между элементами
                outRect.left = insetBetweenDp
                outRect.top = insetSidesDp
                outRect.bottom = insetSidesDp

                // Добавить сдвиг только для первого элемента, чтобы избежать двойного смещения между айтемами
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = insetOutDp
                } else if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
                    outRect.right = insetOutDp
                }
            }
        } else {
            throw IllegalStateException("You probable forgot set the layout manager or chose not LinearLayoutManager")
        }
    }
}