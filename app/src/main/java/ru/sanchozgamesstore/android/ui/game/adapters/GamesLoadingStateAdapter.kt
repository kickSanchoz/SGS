package ru.sanchozgamesstore.android.ui.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.android.utils.shimmerEnabled
import ru.sanchozgamesstore.databinding.ShimmerGameCardBinding

class GamesLoadingStateAdapter : LoadStateAdapter<GamesLoadingStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            ShimmerGameCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ViewHolder(
        private val binding: ShimmerGameCardBinding
    ) : BaseViewHolder<LoadState>(binding.root) {
        override fun bind(data: LoadState) {
            binding.sflRoot.shimmerEnabled(data is LoadState.Loading)
        }
    }
}