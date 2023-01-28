package ru.sanchozgamesstore.android.ui.gallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGalleryDialogElementBinding

class GalleryDialogAdapter: RecyclerView.Adapter<BaseViewHolder<ScreenshotModel>>() {

    private val galleryItemList = mutableListOf<ScreenshotModel>()

    /**
     * Добавить элементы галереи в адаптер
     * */
    fun addAll(galleryItems: List<ScreenshotModel>) {
        galleryItemList.clear()
        galleryItemList.addAll(galleryItems)
        notifyItemRangeChanged(0, galleryItemList.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ScreenshotModel> {
        return GalleryViewHolder(
            ItemGalleryDialogElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ScreenshotModel>, position: Int) {
        holder.bind(galleryItemList[position])
    }

    override fun getItemCount(): Int {
        return galleryItemList.size
    }

    inner class GalleryViewHolder(private val binding: ItemGalleryDialogElementBinding) :
        BaseViewHolder<ScreenshotModel>(binding.root) {
        override fun bind(data: ScreenshotModel) {
            binding.apply {
                pvScreenshot.load(data.image) {
                    defaultPictureLoadParams(root.context)
                }
            }
        }
    }

}