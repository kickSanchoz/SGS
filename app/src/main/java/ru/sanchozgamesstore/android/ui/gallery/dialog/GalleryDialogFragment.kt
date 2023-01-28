package ru.sanchozgamesstore.android.ui.gallery.dialog

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseDialogFragment
import ru.sanchozgamesstore.android.base.BaseDialogFragment.Companion.Configurator
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.sealed.DialogWindowSize
import ru.sanchozgamesstore.android.ui.gallery.GalleryViewModel
import ru.sanchozgamesstore.android.ui.gallery.adapters.GalleryDialogAdapter
import ru.sanchozgamesstore.databinding.DialogFragmentGalleryBinding

@AndroidEntryPoint
class GalleryDialogFragment : BaseDialogFragment<DialogFragmentGalleryBinding>() {

    private val viewModel: GalleryViewModel by viewModels()

    /** Адаптер для элементов галереи */
    private var galleryAdapter: GalleryDialogAdapter? = null

    /** Слушатель на смену состяния элемента viewpager2 */
    private var onGalleryItemChanged = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            /*
            * FIXME: post является костылём.
            *
            * setDefaultAppBarTitle включает/отключает видимость текста в аппбаре, в зависимости от
            * того не является или является переданный текст null соответственно.
            *
            * Для всех страниц viewpager, видимость текста устанавливается верно, за исключением
            * первой.
            * */
            binding.vpGalleryItems.post {
                setDefaultAppBarTitle(
                    getGalleryDialogTitle(
                        position,
                        viewModel.galleryItems.value?.size ?: 0
                    )
                )
            }
        }
    }

    override fun Configurator.getConfigurator() {
        layout = R.layout.dialog_fragment_gallery
        animationStyle = null
        isCancelable = true
        isCloseableOnTouchOutside = false
        height = DialogWindowSize.MatchParent
    }

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let { args ->
            viewModel.setGallerySelectedPosition(
                args.getInt(KEY_GALLERY_SELECTED_ITEM, -1)
            )

            viewModel.galleryItems.value = args.getParcelableArray(KEY_GALLERY_ITEMS)?.map {
                it as ScreenshotModel
            } ?: throw ClassCastException()
        }
    }

    override fun setupView() {
        super.setupView()

        setupDefaultAppBar(
            layout = binding.appBar,
        )

        galleryAdapter = GalleryDialogAdapter()

        binding.vpGalleryItems.apply {
            adapter = galleryAdapter

            registerOnPageChangeCallback(onGalleryItemChanged)
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.galleryItems.observe(viewLifecycleOwner) {

            galleryAdapter?.apply {
                //Добавить все элементы в адаптер
                addAll(it)

                //Открыть нужный элемент галереи
                binding.vpGalleryItems.setCurrentItem(
                    viewModel.getGallerySelectedPosition(),
                    false
                )
            }
        }
    }

    override fun onDestroyView() {
        binding.vpGalleryItems.unregisterOnPageChangeCallback(onGalleryItemChanged)
        super.onDestroyView()
        galleryAdapter = null
    }

    /**
     * Получить строку для аппбара
     * */
    private fun getGalleryDialogTitle(pos: Int, total: Int): String {
        return getString(R.string.NumberOutOfTotal, pos + 1, total)
    }

    companion object {
        /** Bundle-key списка элементов галереи */
        private const val KEY_GALLERY_ITEMS = "key.gallery_items"

        /** Bundle-key элемента, который необходимо отобразить в галереи */
        private const val KEY_GALLERY_SELECTED_ITEM = "key.gallery_selected_item"

        /**
         * Получить bundle, необходимый для открытия диалог фрагмента
         * @param galleryItems список скриншотов
         * */
        fun getBundle(galleryItems: List<ScreenshotModel>, selectedItem: Int): Bundle {
            return Bundle().apply {
                putParcelableArray(KEY_GALLERY_ITEMS, galleryItems.toTypedArray())
                putInt(KEY_GALLERY_SELECTED_ITEM, selectedItem)
            }
        }
    }
}