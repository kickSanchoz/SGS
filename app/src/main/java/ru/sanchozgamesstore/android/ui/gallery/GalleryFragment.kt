package ru.sanchozgamesstore.android.ui.gallery

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.ui.gallery.adapters.GalleryAdapter
import ru.sanchozgamesstore.android.ui.gallery.dialog.GalleryDialogFragment
import ru.sanchozgamesstore.android.utils.itemDecoration.VerticalGridItemDecoration
import ru.sanchozgamesstore.databinding.FragmentGalleryBinding

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

    private val viewModel: GalleryViewModel by viewModels()

    private var galleryAdapter: GalleryAdapter? = null

    override fun getLayoutID(): Int = R.layout.fragment_gallery

    override fun parseArguments() {
        super.parseArguments()

        val args: GalleryFragmentArgs by navArgs()
        viewModel.galleryItems.value = args.galleryItems.toList()
        viewModel.title = args.title
    }

    override fun setUpViews() {
        super.setUpViews()

        setupDefaultAppBar(
            layout = binding.appBar,
            title = viewModel.title,
        )

        galleryAdapter = GalleryAdapter()

        binding.apply {
            rvGallery.apply {
                adapter = galleryAdapter

                layoutManager = GridLayoutManager(root.context, SPAN_COUNT)

                //TODO добавить отступы по бокам
                addItemDecoration(VerticalGridItemDecoration(8, 8))
            }
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.galleryItems.observe(viewLifecycleOwner) {
            galleryAdapter?.apply {
                addAll(it)

                setOnScreenshotClickListener { pos ->
                    GalleryDialogFragment().apply {
                        arguments = GalleryDialogFragment.getBundle(
                            galleryItems = it,
                            selectedItem = pos
                        )
                    }.show(childFragmentManager, null)
                }
            }
        }
    }

    companion object {
        const val SPAN_COUNT = 3
    }
}