package ru.sanchozgamesstore.android.ui.gallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGalleryScreenshotBinding

class GalleryAdapter : RecyclerView.Adapter<BaseViewHolder<ScreenshotModel>>() {

    private val galleryItemList = mutableListOf<ScreenshotModel>()

    /**
     * Действие при клике на скриншот
     * */
    private var onScreenshotClickListener: ((position: Int) -> Unit)? = null

    /**
     * Добавить элементы галереи в адаптер
     * */
    fun addAll(galleryItems: List<ScreenshotModel>) {
        galleryItemList.clear()
        galleryItemList.addAll(galleryItems)
        notifyItemRangeChanged(0, galleryItemList.size)
    }

    /**
     * Установить действие при клике на скриншот
     * */
    fun setOnScreenshotClickListener(block: (position: Int) -> Unit) {
        onScreenshotClickListener = block
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ScreenshotModel> {
        return GalleryViewHolder(
            ItemGalleryScreenshotBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ScreenshotModel>, position: Int) {
        holder.bind(galleryItemList[position], position)
    }

    override fun getItemCount(): Int {
        return galleryItemList.size
    }

    inner class GalleryViewHolder(private val binding: ItemGalleryScreenshotBinding) :
        BaseViewHolder<ScreenshotModel>(binding.root) {
        override fun bind(data: ScreenshotModel, position: Int) {
            binding.apply {
                ivScreenshot.load(data.image) {
                    defaultPictureLoadParams(root.context)
                }

                ivScreenshot.setOnClickListener {
                    onScreenshotClickListener?.invoke(
                        position
                    )
                }
            }
        }
    }

}