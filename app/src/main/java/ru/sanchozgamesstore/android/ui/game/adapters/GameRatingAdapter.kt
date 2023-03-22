package ru.sanchozgamesstore.android.ui.game.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.data.domain.enums.RatingSpecies
import ru.sanchozgamesstore.android.data.domain.models.rating.RatingModel
import ru.sanchozgamesstore.databinding.ItemGameRatingBinding

class GameRatingAdapter : RecyclerView.Adapter<GameRatingAdapter.GameRatingViewHolder>() {

    private val ratingList = mutableListOf<Map.Entry<RatingSpecies, RatingModel>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRatingViewHolder {
        return GameRatingViewHolder(
            ItemGameRatingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameRatingViewHolder, position: Int) {
        holder.bind(ratingList[position])
    }

    override fun getItemCount(): Int {
        return ratingList.size
    }

    /**
     * Добавить все рейтинги в адаптер
     *
     * @param ratings список пар "тип рейтинга" - "модель рейтинга"
     * */
    fun addAll(ratings: List<Map.Entry<RatingSpecies, RatingModel>>) {
        ratingList.clear()
        ratingList.addAll(ratings)
        notifyDataSetChanged()
    }

    class GameRatingViewHolder(private val binding: ItemGameRatingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rating: Map.Entry<RatingSpecies, RatingModel>) {
            binding.apply {
                val species = rating.key
                val model = rating.value

                vColor.backgroundTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        binding.root.resources,
                        species.color,
                        null
                    )
                )

                tvName.text = model.title
                tvCount.text = model.count.toString()
            }
        }
    }
}