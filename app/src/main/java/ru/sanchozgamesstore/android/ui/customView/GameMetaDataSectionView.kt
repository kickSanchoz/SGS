package ru.sanchozgamesstore.android.ui.customView

import android.view.LayoutInflater
import androidx.core.view.isVisible
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseViewItem
import ru.sanchozgamesstore.android.data.domain.models.game.metaData.GameMetaData
import ru.sanchozgamesstore.databinding.ViewGameMetaDataBinding

class GameMetaDataSectionView(
    binding: ViewGameMetaDataBinding
) : BaseViewItem<ViewGameMetaDataBinding>(binding) {

    /**
     * Установить раздел метаданных
     *
     * @param section данные раздела метаданных
     * */
    fun setSectionData(section: GameMetaData) {
        binding.apply {
            tvTitle.text = root.context.getString(section.header.title)

            tvEmpty.isVisible = section.sequence.isNullOrBlank()
            tvSequence.isVisible = !section.sequence.isNullOrBlank()

            tvSequence.text = section.sequence
        }
    }

    companion object {
        fun bind(layoutInflater: LayoutInflater): GameMetaDataSectionView {
            return bind(
                layoutInflater,
                R.layout.view_game_meta_data
            )
        }
    }
}