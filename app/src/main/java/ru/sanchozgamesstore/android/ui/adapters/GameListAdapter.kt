package ru.sanchozgamesstore.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.utils.getColorByScore
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameCardBinding

class GameListAdapter :
    ListAdapter<GameDetailsModel, GameListAdapter.GamesListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesListViewHolder {
        return GamesListViewHolder(
            ItemGameCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GamesListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    class GamesListViewHolder(private val binding: ItemGameCardBinding) : ViewHolder(binding.root) {
        fun bind(gameDetails: GameDetailsModel) {
            binding.apply {
                ivBackground.apply image@{
                    load(gameDetails.background_image) {
                        pictureLoadParams(this@image)
                    }
                }

                cvScore.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        binding.root.resources,
                        getColorByScore(gameDetails.metacritic),
                        null
                    )
                )
                tvScore.text = gameDetails.metacritic.toString()

                tvTitle.text = gameDetails.name
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<GameDetailsModel> =
            object : DiffUtil.ItemCallback<GameDetailsModel>() {
                override fun areItemsTheSame(
                    oldItem: GameDetailsModel,
                    newItem: GameDetailsModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: GameDetailsModel,
                    newItem: GameDetailsModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}