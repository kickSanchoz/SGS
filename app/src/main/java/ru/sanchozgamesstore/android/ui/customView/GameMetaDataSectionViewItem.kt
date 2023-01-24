package ru.sanchozgamesstore.android.ui.customView

import android.view.LayoutInflater
import androidx.core.view.isVisible
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseViewItem
import ru.sanchozgamesstore.android.data.domain.models.game.metadata.GameMetadata
import ru.sanchozgamesstore.databinding.ViewItemGameMetaDataBinding

class GameMetaDataSectionViewItem(
    binding: ViewItemGameMetaDataBinding
) : BaseViewItem<ViewItemGameMetaDataBinding>(binding) {

    /**
     * Установить раздел метаданных
     *
     * @param section данные раздела метаданных
     * */
    fun setSectionData(section: GameMetadata) {
        binding.apply {
            tvTitle.text = root.context.getString(section.header.title)

            tvEmpty.isVisible = section.sequence.isNullOrBlank()
            tvSequence.isVisible = !section.sequence.isNullOrBlank()

            tvSequence.text = section.sequence
        }
    }

    companion object {
        fun bind(layoutInflater: LayoutInflater): GameMetaDataSectionViewItem {
            return bind(
                layoutInflater,
                R.layout.view_item_game_meta_data
            )
        }
    }
}