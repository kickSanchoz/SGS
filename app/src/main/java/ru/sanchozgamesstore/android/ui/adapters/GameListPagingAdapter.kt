package ru.sanchozgamesstore.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.utils.getColorByScore
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameCardBinding

class GameListPagingAdapter : PagingDataAdapter<GameDetailsModel, GameListPagingAdapter.ViewHolder>(
    GameListAdapter.DIFF_CALLBACK
) {
    private var onClickListener: ((game: GameDetailsModel) -> Unit)? = null

    fun setOnClickListener(block: (game: GameDetailsModel) -> Unit) {
        onClickListener = block
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemGameCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemGameCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
}