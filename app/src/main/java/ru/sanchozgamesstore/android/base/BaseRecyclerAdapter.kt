package ru.sanchozgamesstore.android.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    /**
     * Редактируемый список элементов
     * */
    private val _itemsList = mutableListOf<T>()

    /**
     * Список элементов адаптера
     * */
    val itemsList: List<T>
        get() = _itemsList

    /**
     * Очистить список
     * */
    fun clear() {
        val size = itemCount
        _itemsList.clear()
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Добавить элементы в список
     * */
    open fun addAll(items: List<T>) {
        val size = itemCount
        _itemsList.addAll(items)
        notifyItemRangeChanged(size, itemsList.size)
    }

    /**
     * Установить данные в список
     * */
    fun submitData(items: List<T>) {
        val size = itemCount
        _itemsList.clear()
        _itemsList.addAll(items)
        notifyItemRangeRemoved(0, size)
    }

    override fun getItemCount(): Int = itemsList.size

}