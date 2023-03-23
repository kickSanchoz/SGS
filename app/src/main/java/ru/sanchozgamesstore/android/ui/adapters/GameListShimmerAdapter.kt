package ru.sanchozgamesstore.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.sanchozgamesstore.android.base.BaseRecyclerAdapter
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.databinding.ItemShimmerGameCardBinding

class GameListShimmerAdapter : BaseRecyclerAdapter<Unit>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Unit> {
        return GamesListShimmerViewHolder(
            ItemShimmerGameCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Unit>, position: Int) {
        holder.bind(itemsList[position])
    }

    class GamesListShimmerViewHolder(private val binding: ItemShimmerGameCardBinding) :
        BaseViewHolder<Unit>(binding.root) {
        override fun bind(data: Unit) {

        }
    }
}