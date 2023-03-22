package ru.sanchozgamesstore.android.ui.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameScreenshotBinding
import ru.sanchozgamesstore.databinding.ItemGameScreenshotViewAllBinding

class GameScreenshotAdapter :
    RecyclerView.Adapter<BaseViewHolder<ScreenshotModel>>() {

    private val screenshotList = mutableListOf<ScreenshotModel>()

    /**
     * Число видимых элементов
     * */
    private var cutCount: Int = CUT_COUNT

    /**
     * Действие при клике на скриншот
     * */
    private var onScreenshotClickListener: ((position: Int) -> Unit)? = null

    /**
     * Действие при клике на скриншот-декорацию
     * */
    private var onViewAllClickListener: (() -> Unit)? = null

    /**
     * Добавление скриншотов в адаптер
     * */
    fun addAll(screenshots: List<ScreenshotModel>) {
        screenshotList.clear()
        screenshotList.addAll(screenshots)
        notifyItemRangeChanged(0, screenshotList.size)
    }

    /**
     * Установить число видимых элементов
     * */
    fun setCutCount(value: Int) {
        if (value >= MIN_CUT_COUNT) {
            cutCount = value
        } else {
            throw IllegalStateException("Cut count must be > $MIN_CUT_COUNT")
        }
    }

    /**
     * Установить действие при клике на скриншот
     * */
    fun setOnScreenshotClickListener(block: (position: Int) -> Unit) {
        onScreenshotClickListener = block
    }

    /**
     * Установить действие при клике на скриншот-декорацию
     * */
    fun setOnViewAllClickListener(block: () -> Unit) {
        onViewAllClickListener = block
    }

    override fun getItemViewType(position: Int): Int {
        return if (isCutPosition(position)) {
            CUT_ITEM
        } else {
            DEFAULT_ITEM
        }
    }

    /**
     * Является ли текущая позиция айтемом, открывающим галерею скриншотов
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
        when (holder.itemViewType) {
            DEFAULT_ITEM -> {
                holder.bind(screenshotList[position], position)
            }
            CUT_ITEM -> {
                holder.bind(screenshotList[position])
            }
            else -> {
                throw IllegalStateException("Unknown view type on bind view holder")
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isScreenshotsMoreThanCutCount()) {
            cutCount
        } else {
            screenshotList.size
        }
    }

    inner class DefaultItemViewHolder(private val binding: ItemGameScreenshotBinding) :
        BaseViewHolder<ScreenshotModel>(binding.root) {
        override fun bind(data: ScreenshotModel, position: Int) {
            binding.apply {
                cvScreenshot.setOnClickListener {
                    onScreenshotClickListener?.invoke(
                        position
                    )
                }

                ivScreenshot.apply image@{
                    load(data.image) {
                        pictureLoadParams(this@image)
                    }
                }
            }
        }
    }

    inner class CutItemViewHolder(private val binding: ItemGameScreenshotViewAllBinding) :
        BaseViewHolder<ScreenshotModel>(binding.root) {
        override fun bind(data: ScreenshotModel) {
            binding.apply {
                cvScreenshot.setOnClickListener {
                    onViewAllClickListener?.invoke()
                }

                ivScreenshot.alpha = CUT_ITEM_ALPHA

                ivScreenshot.apply image@{
                    load(data.image) {
                        pictureLoadParams(this@image)
                    }
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