package ru.sanchozgamesstore.android.ui.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sanchozgamesstore.android.data.domain.models.game.metadata.GameMetadata
import ru.sanchozgamesstore.android.ui.customView.GameMetaDataSectionViewItem

class GameMetadataAdapter : RecyclerView.Adapter<GameMetadataAdapter.GameMetadataViewHolder>() {

    private val sectionList = mutableListOf<GameMetadata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameMetadataViewHolder {
        return GameMetadataViewHolder(
            GameMetaDataSectionViewItem.bind(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: GameMetadataViewHolder, position: Int) {
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
        notifyItemRangeChanged(0, sectionList.size)
    }

    class GameMetadataViewHolder(private val binding: GameMetaDataSectionViewItem) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(section: GameMetadata) {
            binding.setSectionData(section)
        }
    }
}