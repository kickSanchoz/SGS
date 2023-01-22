package ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameScreenshotBinding
import ru.sanchozgamesstore.databinding.ItemGameScreenshotViewAllBinding

class GameScreenshotAdapter :
    RecyclerView.Adapter<BaseViewHolder<ScreenshotModel>>() {

    private val screenshotList = mutableListOf<ScreenshotModel>()

    /**
     * Число видимых элементов
     * */
    private var cutCount: Int = CUT_COUNT

    override fun getItemViewType(position: Int): Int {
        return if (isCutPosition(position)) {
            CUT_ITEM
        } else {
            DEFAULT_ITEM
        }
    }

    /**
     * Является ли текущая позиция айтемом, открывающим галлерею скриншотов
     * */
    private fun isCutPosition(position: Int): Boolean {
        /*
        * Если размер списка больше, чем установленное число видимых элеметов (т.е список был
        * обрезан) и текущая позиция равна последнему видимому элементу
        * */
        return isScreenshotsMoreThanCutCount() && position == cutCount - 1
    }

    /**
     * Рамзер списка больше, чем установленное число видимых скриншотов
     * */
    private fun isScreenshotsMoreThanCutCount(): Boolean {
        return screenshotList.size > cutCount
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ScreenshotModel> {
        return when (viewType) {
            DEFAULT_ITEM -> {
                DefaultItemViewHolder(
                    ItemGameScreenshotBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }
            CUT_ITEM -> {
                CutItemViewHolder(
                    ItemGameScreenshotViewAllBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw IllegalStateException("Unknown view type on create view holder")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ScreenshotModel>, position: Int) {
        holder.bind(screenshotList[position])
    }

    override fun getItemCount(): Int {
        return if (isScreenshotsMoreThanCutCount()) {
            cutCount
        } else {
            screenshotList.size
        }
    }

    /**
     * Добавление скриншотов в адаптер
     * */
    fun addAll(screenshots: List<ScreenshotModel>) {
        screenshotList.clear()
        screenshotList.addAll(screenshots)
        notifyDataSetChanged()
    }

    /**
     * Установить число видимых элементов
     * */
    fun setCutCount(value: Int) {
        if (value >= MIN_CUT_COUNT) {
            cutCount = value
        } else {
            throw IllegalStateException("Cut count must be > 1")
        }
    }

    class DefaultItemViewHolder(private val binding: ItemGameScreenshotBinding) :
        BaseViewHolder<ScreenshotModel>(binding.root) {
        override fun bind(data: ScreenshotModel) {
            binding.apply {
                cvScreenshot.setOnClickListener {

                }

                ivScreenshot.load(data.image) {
                    defaultPictureLoadParams(binding.root.context)
                }
            }
        }
    }

    class CutItemViewHolder(private val binding: ItemGameScreenshotViewAllBinding) :
        BaseViewHolder<ScreenshotModel>(binding.root) {
        override fun bind(data: ScreenshotModel) {
            binding.apply {
                cvScreenshot.setOnClickListener {

                }

                ivScreenshot.alpha = CUT_ITEM_ALPHA

                ivScreenshot.load(data.image) {
                    defaultPictureLoadParams(binding.root.context)
                }
            }
        }
    }

    companion object {
        const val CUT_COUNT = 3
        const val MIN_CUT_COUNT = 1
        const val CUT_ITEM_ALPHA = 0.25f

        const val DEFAULT_ITEM = 1
        const val CUT_ITEM = 2
    }
}