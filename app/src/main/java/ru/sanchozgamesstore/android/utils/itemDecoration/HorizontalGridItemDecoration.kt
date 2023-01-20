package ru.sanchozgamesstore.android.utils.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.utils.px
import kotlin.math.ceil

class HorizontalGridItemDecoration(
    horizontalSpace: Int = 0,
    verticalSpace: Int = 0,
) : RecyclerView.ItemDecoration() {

    /**
     * Оступы между колонками таблицы
     * */
    private val horizontalSpaceDp = horizontalSpace.px

    /**
     * Оступы между строками таблицы
     * */
    private val verticalSpaceDp = verticalSpace.px


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

        //Этот декоратор может работать только с вертикальной таблицей :(
        if (layoutManager.orientation != RecyclerView.VERTICAL) {
            throw IllegalStateException(
                "Unfortunately, this decorator can only work with vertical orientation layout"
            )
        }

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

        setHorizontalSpace(
            outRect = outRect,
            position = position,
            spanCount = spanCount,
        )

        setVerticalSpace(
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

        return colPos == 0
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
    ): Boolean {
        //Номер текущей колонки
        val colPos = getColumnPosition(
            pos = pos,
            spanCount = spanCount,
        )

        return colPos == spanCount - 1
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

        return rowPos == 0
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

        //Всего строк в таблице
        val totalRow = ceil(listSize.toDouble() / spanCount).toInt()

        return rowPos == totalRow - 1

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
        //Остаток от деления == номер колонки
        return pos % spanCount
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
        //Результат деления (с округлением в меньшую сторону) == номер строки
        return pos / spanCount
    }

    /**
     * Установить ГОРИЗОНТАЛЬНЫЕ отступы между элементами
     *
     * @param outRect размеры элемента списка
     * @param position позиция элемента в списке
     * @param spanCount число столбцов (ВЕРТИКАЛЬНАЯ ориентация) или строк (ГОРИЗОНТАЛЬНАЯ ориентация)
     * */
    private fun setHorizontalSpace(
        outRect: Rect,
        position: Int,
        spanCount: Int,
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
                outRect.right = horizontalSpaceDp / 2
            }
            isLastColumn -> {
                //Для последней колонки отступ только СЛЕВА
                outRect.left = horizontalSpaceDp / 2
            }
            else -> {
                //Для всех остальных отступ и СЛЕВА, и СПРАВА
                outRect.left = horizontalSpaceDp / 2
                outRect.right = horizontalSpaceDp / 2
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
    private fun setVerticalSpace(
        outRect: Rect,
        position: Int,
        spanCount: Int,
        listSize: Int
    ) {
        //Текущая позиция элемента является находится в последней строке таблицы
        val isLastRow = isLastRow(
            pos = position,
            spanCount = spanCount,
            listSize = listSize,
        )

        //Для всех элементов задаём нижний оступ, кроме элементов последней строки
        if (!isLastRow) {
            outRect.bottom = verticalSpaceDp
        }
    }
}