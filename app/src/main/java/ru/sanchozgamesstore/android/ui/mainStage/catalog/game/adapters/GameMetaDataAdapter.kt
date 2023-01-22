package ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.data.domain.models.game.metadata.GameMetadata
import ru.sanchozgamesstore.android.ui.customView.GameMetaDataSectionView

class GameMetaDataAdapter : RecyclerView.Adapter<GameMetaDataAdapter.GameMetaDataViewHolder>() {

    private val sectionList = mutableListOf<GameMetadata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameMetaDataViewHolder {
        return GameMetaDataViewHolder(
            GameMetaDataSectionView.bind(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: GameMetaDataViewHolder, position: Int) {
        holder.bind(sectionList[position])
    }

    override fun getItemCount(): Int {
        return sectionList.size
    }

    /**
     * Добавлить разделы в адаптер
     * */
    fun addAll(sections: List<GameMetadata>) {
        sectionList.clear()
        sectionList.addAll(sections)
        notifyDataSetChanged()
    }

    class GameMetaDataViewHolder(private val binding: GameMetaDataSectionView) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(section: GameMetadata) {
            binding.setSectionData(section)
        }
    }
}