package ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.data.domain.models.platform.MetacriticPlatformModel
import ru.sanchozgamesstore.databinding.ItemGameMetacriticBinding

class GameMetacriticAdapter :
    RecyclerView.Adapter<GameMetacriticAdapter.GameMetacriticViewHolder>() {

    private val metacriticPlatformList = mutableListOf<MetacriticPlatformModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameMetacriticViewHolder {
        return GameMetacriticViewHolder(
            ItemGameMetacriticBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameMetacriticViewHolder, position: Int) {
        holder.bind(metacriticPlatformList[position])
    }

    override fun getItemCount(): Int {
        return metacriticPlatformList.size
    }

    /**
     * Добавлить оценки игры от метакритики по каждой платформе в адаптер
     * */
    fun addAll(metacriticPlatforms: List<MetacriticPlatformModel>) {
        metacriticPlatformList.clear()
        metacriticPlatformList.addAll(metacriticPlatforms)
        notifyDataSetChanged()
    }

    class GameMetacriticViewHolder(private val binding: ItemGameMetacriticBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(metacriticPlatform: MetacriticPlatformModel) {
            binding.apply {
                cvScore.setCardBackgroundColor(
                    ResourcesCompat.getColor(
                        binding.root.resources,
                        metacriticPlatform.colorByMetascore,
                        null
                    )
                )

                tvScore.text = metacriticPlatform.metascore.toString()
                tvPlatform.text = metacriticPlatform.platform.name
            }
        }
    }
}