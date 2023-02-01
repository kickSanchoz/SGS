package ru.sanchozgamesstore.android.utils.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.utils.px
import kotlin.math.ceil

/**
 * @param spaceBetween_columns Расстояние между колонками
 * @param spaceBetween_rows Расстояние между строками
 * @param spaceOut Расстояние снаружи ресайклера:
 *
 * для горизонтального лейаута - слева, справа;
 *
 * для вертикального лейаута - сверху, снизу.
 * @param spaceSides Расстояние по сторонам ресайклера:
 *
 * для горизонтального лейаута - сверху, снизу;
 *
 * для вертикального лейаута - слева, справа.
 * */
/*
* FIXME: при горизонтальной ориентации и spanCount > 1 отступы дублируются на некоторых элементах
* Например, orientation=horizontal и spanCount = 2, растояние между строками устанавливается
* для всех строк, кроме последней. Но в данной реализации отступ добавляется и для последней
* строки(???)
* */
class GridItemDecoration(
    spaceBetween_columns: Int = 0,
    spaceBetween_rows: Int = 0,
) : RecyclerView.ItemDecoration() {

    /**
     * Оступы между колонками таблицы
     * */
    private val spaceBetweenColumnsDp = spaceBetween_columns.px

    /**
     * Оступы между строками таблицы
     * */
    private val spaceBetweenRowsDp = spaceBetween_rows.px

    /**
     * Ориентация RecyclerView.
     *
     * При прямом направлении добавления элементов (reverseLayout = false)
     * VERTICAL - элементы добавляются слева направо, при переносе добавляется строка снизу
     * HORIZONTAL - элементы добавляются сверху вниз, при переносе добавляется колонка справа
     * */
    private var layoutOrientation: Int? = null

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val layoutManager = try {
            parent.layoutManager as GridLayoutManager
        } catch (e: Exception) {
            throw IllegalStateException(
                "You can't add this decorator except for the GridLayoutManager" +
                        "\nreason: ${e.message}"
            )
        }

        layoutOrientation = layoutManager.orientation

        //Этот декоратор может работать только с передним направлением таблицы :(
        if (layoutManager.reverseLayout) {
            throw IllegalStateException(
                "Unfortunately, this decorator can only work with front direction layout"
            )
        }

        //Число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
        val spanCount = layoutManager.spanCount

        //Размер всего списка
        val itemsCount = state.itemCount

        //Позиция элемента в списке
        val position = parent.getChildAdapterPosition(view)

        setSpaceBetweenColumns(
            outRect = outRect,
            position = position,
            spanCount = spanCount,
            listSize = itemsCount,
        )

        setSpaceBetweenRows(
            outRect = outRect,
            position = position,
            spanCount = spanCount,
            listSize = itemsCount,
        )
    }

    /**
     * Является ли данная позиция элемента списка первым столбцом таблицы
     *
     * @param pos позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * */
    private fun isFirstColumn(
        pos: Int,
        spanCount: Int,
    ): Boolean {
        //Номер текущей колонки
        val colPos = getColumnPosition(
            pos = pos,
            spanCount = spanCount
        )

        return when (layoutOrientation) {
            RecyclerView.VERTICAL, RecyclerView.HORIZONTAL -> {
                colPos == 0
            }
            else -> {
                throw IllegalStateException(LAYOUT_NOT_INITIALIZED)
            }
        }
    }

    /**
     * Является ли данная позиция элемента списка последним столбцом таблицы
     *
     * @param pos позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * */
    private fun isLastColumn(
        pos: Int,
        spanCount: Int,
        listSize: Int,
    ): Boolean {
        //Номер текущей колонки
        val colPos = getColumnPosition(
            pos = pos,
            spanCount = spanCount,
        )

        return when (layoutOrientation) {
            RecyclerView.VERTICAL -> {
                colPos == spanCount - 1
            }
            RecyclerView.HORIZONTAL -> {
                //Всего столбцов в таблице
                val totalColumns = ceil(listSize.toDouble() / spanCount).toInt()

                colPos == totalColumns - 1
            }
            else -> {
                throw IllegalStateException(LAYOUT_NOT_INITIALIZED)
            }
        }
    }

    /**
     * Является ли данная позиция элемента списка первой строкой таблицы
     *
     * @param pos позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * */
    private fun isFirstRow(
        pos: Int,
        spanCount: Int,
    ): Boolean {
        //Номер текущей строки
        val rowPos = getRowPosition(
            pos = pos,
            spanCount = spanCount
        )

        return when (layoutOrientation) {
            RecyclerView.VERTICAL, RecyclerView.HORIZONTAL -> {
                rowPos == 0
            }
            else -> {
                throw IllegalStateException(LAYOUT_NOT_INITIALIZED)
            }
        }
    }

    /**
     * Является ли данная позиция элемента списка последней строкой таблицы
     *
     * @param pos позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * @param listSize размер всего списка
     * */
    private fun isLastRow(
        pos: Int,
        spanCount: Int,
        listSize: Int,
    ): Boolean {
        //Номер текущей строки
        val rowPos = getRowPosition(
            pos = pos,
            spanCount = spanCount,
        )

        return when (layoutOrientation) {
            RecyclerView.VERTICAL -> {
                //Всего строк в таблице
                val totalRows = ceil(listSize.toDouble() / spanCount).toInt()

                rowPos == totalRows - 1
            }
            RecyclerView.HORIZONTAL -> {
                rowPos == spanCount - 1
            }
            else -> {
                throw IllegalStateException(LAYOUT_NOT_INITIALIZED)
            }
        }

    }

    /**
     * Получить позицию колонки в таблице для текущего элемента
     *
     * @param pos позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * */
    private fun getColumnPosition(
        pos: Int,
        spanCount: Int
    ): Int {
        return when (layoutOrientation) {
            RecyclerView.VERTICAL -> {
                //Остаток от деления == номер колонки
                pos % spanCount
            }
            RecyclerView.HORIZONTAL -> {
                //Результат деления (с округлением в меньшую сторону) == номер колонки
                return pos / spanCount
            }
            else -> {
                throw IllegalStateException(LAYOUT_NOT_INITIALIZED)
            }
        }


    }

    /**
     * Получить позицию строки в таблице для текущего элемента
     *
     * @param pos позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * */
    private fun getRowPosition(
        pos: Int,
        spanCount: Int
    ): Int {
        return when (layoutOrientation) {
            RecyclerView.VERTICAL -> {
                //Результат деления (с округлением в меньшую сторону) == номер строки
                pos / spanCount
            }
            RecyclerView.HORIZONTAL -> {
                //Остаток от деления == номер строки
                pos % spanCount
            }
            else -> {
                throw IllegalStateException(LAYOUT_NOT_INITIALIZED)
            }
        }
    }

    /**
     * Установить ГОРИЗОНТАЛЬНЫЕ отступы между элементами
     *
     * @param outRect размеры элемента списка
     * @param position позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * @param listSize размер всего списка
     * */
    private fun setSpaceBetweenColumns(
        outRect: Rect,
        position: Int,
        spanCount: Int,
        listSize: Int,
    ) {
        /*
        * Если число столбцов <= 1, то устанавливать горизонтальные отступы не нужно
        * */
        if (spanCount <= 1) {
            return
        }

        //Текущая позиция элемента является первой колонкой таблицы
        val isFirstColumn = isFirstColumn(
            pos = position,
            spanCount = spanCount,
        )

        //Текущая позиция элемента является последней колонкой таблицы
        val isLastColumn = isLastColumn(
            pos = position,
            spanCount = spanCount,
            listSize = listSize
        )

        /*
        * Т.к. отступ между элементами должен быть равен задонному числу, то чтобы элементы были
        * равных размеров задаём дополнительные оступы для каждого элемента в половину заданного
        * значения
        *
        * |---------|          |---------|          |---------|
        * |         |  <-20->  |         |  <-20->  |         |
        * |---------|          |---------|          |---------|
        *
        *                       ---vvv---
        *
        * |---------|          |---------|          |---------|
        * |         | 10-><-10 |         | 10-><-10 |         |
        * |---------|          |---------|          |---------|
        * */
        when {
            isFirstColumn -> {
                //Для первой колонки отступ только СПРАВА
                outRect.right = spaceBetweenColumnsDp / 2
            }
            isLastColumn -> {
                //Для последней колонки отступ только СЛЕВА
                outRect.left = spaceBetweenColumnsDp / 2
            }
            else -> {
                //Для всех остальных отступ и СЛЕВА, и СПРАВА
                outRect.left = spaceBetweenColumnsDp / 2
                outRect.right = spaceBetweenColumnsDp / 2
            }
        }
    }

    /**
     * Установить ВЕРТИКАЛЬНЫЕ отступы между элементами
     *
     * @param outRect размеры элемента списка
     * @param position позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * @param listSize размер всего списка
     * */
    private fun setSpaceBetweenRows(
        outRect: Rect,
        position: Int,
        spanCount: Int,
        listSize: Int,
    ) {
        //Текущая позиция элемента является находится в последней строке таблицы
        val isLastRow = isLastRow(
            pos = position,
            spanCount = spanCount,
            listSize = listSize,
        )

        //Для всех элементов задаём нижний оступ, кроме элементов последней строки
        if (!isLastRow) {
            outRect.bottom = spaceBetweenRowsDp
        }
    }

    companion object {
        const val LAYOUT_NOT_INITIALIZED = "Layout orientation variable hasn't been initialized"
    }
}