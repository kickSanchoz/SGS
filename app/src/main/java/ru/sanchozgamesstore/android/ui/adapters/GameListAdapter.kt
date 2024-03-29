package ru.sanchozgamesstore.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.utils.getColorByScore
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameCardBinding

class GameListAdapter :
    ListAdapter<GameDetailsModel, GameListAdapter.GamesListViewHolder>(DIFF_CALLBACK) {

    private var onClickListener: ((game: GameDetailsModel) -> Unit)? = null

    fun setOnClickListener(block: (game: GameDetailsModel) -> Unit) {
        onClickListener = block
    }

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


    inner class GamesListViewHolder(private val binding: ItemGameCardBinding) :
        ViewHolder(binding.root) {
        fun bind(gameDetails: GameDetailsModel) {
            binding.apply {
                ivBackground.apply image@{
                    load(gameDetails.background_image) {
                        pictureLoadParams(this@image)
                    }
                }

                cvScore.isVisible = gameDetails.metacritic != null
                gameDetails.metacritic?.let {
                    cvScore.setCardBackgroundColor(
                        ResourcesCompat.getColor(
                            binding.root.resources,
                            getColorByScore(it),
                            null
                        )
                    )
                }

                tvScore.text = gameDetails.metacritic.toString()

                tvTitle.text = gameDetails.name

                cvGame.setOnClickListener {
                    onClickListener?.invoke(gameDetails)
                }
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        onClickListener = null
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GameDetailsModel> =
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