package ru.sanchozgamesstore.android.ui.mainStage.catalog

import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.ui.adapters.GameListPagingAdapter
import ru.sanchozgamesstore.android.ui.adapters.GameListShimmerAdapter
import ru.sanchozgamesstore.android.utils.DeepLinkController
import ru.sanchozgamesstore.android.utils.hideKeyboard
import ru.sanchozgamesstore.android.utils.itemDecoration.GridItemDecoration
import ru.sanchozgamesstore.databinding.FragmentCatalogBinding


@AndroidEntryPoint
class CatalogFragment : BaseFragment<FragmentCatalogBinding>() {

    private val viewModel: CatalogViewModel by viewModels()

    /**
     * Адаптер списка избранных игр пользователя
     * */
    private var gameListPagingAdapter: GameListPagingAdapter? = null

    /**
     * Адаптер шиммера для списка игр
     * */
    private var gameListShimmerAdapter: GameListShimmerAdapter? = null

    override fun getLayoutID(): Int = R.layout.fragment_catalog

    override fun setUpViews() {
        super.setUpViews()
        binding.vm = viewModel

        gameListPagingAdapter = GameListPagingAdapter().apply {
            setOnClickListener {
                DeepLinkController(this@CatalogFragment).openGamePageFragment(it.id)
            }
        }

        gameListShimmerAdapter = GameListShimmerAdapter().apply {
            submitData(List(6) {})
        }

        binding.apply {
            blockContent.lShimmer.rvShimmer.apply {
                adapter = gameListShimmerAdapter
                layoutManager = GridLayoutManager(context, GAMES_SPAN_COUNT)
                addItemDecoration(GridItemDecoration(8, 8))
            }

            blockContent.rvGames.apply {
                adapter = gameListPagingAdapter
                layoutManager = GridLayoutManager(context, GAMES_SPAN_COUNT)
                addItemDecoration(GridItemDecoration(8, 8))
            }
        }

    }

    override fun observeView() {
        super.observeView()

        binding.apply {
            etSearch.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.performSearch()

                    hideKeyboard()
                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }
        }


    }

    override fun observeData() {
        super.observeData()

        viewModel.games.observe(viewLifecycleOwner) {
            Log.e("PagingData", "$it")
            gameListPagingAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        gameListPagingAdapter = null
        gameListShimmerAdapter = null
    }

    companion object {
        private const val GAMES_SPAN_COUNT = 2
    }
}