package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status
import ru.sanchozgamesstore.android.ui.customView.RatingBarView
import ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters.*
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.android.utils.itemDecoration.HorizontalGridItemDecoration
import ru.sanchozgamesstore.android.utils.itemDecoration.OrientationItemDecoration
import ru.sanchozgamesstore.android.utils.reducedString
import ru.sanchozgamesstore.android.utils.removeItemDecorations
import ru.sanchozgamesstore.android.utils.shimmerEnabled
import ru.sanchozgamesstore.databinding.FragmentGamePageBinding

@AndroidEntryPoint
class GamePageFragment : BaseFragment<FragmentGamePageBinding>() {

    private val viewModel: GamePageViewModel by viewModels()

    private var ratingBarView: RatingBarView? = null

    /** Адаптер родительских платформ */
    private var gameParentPlatformAdapter: GameParentPlatformAdapter? = null

    /** Адаптер скриншотов */
    private var gameScreenshotAdapter: GameScreenshotAdapter? = null

    /** Адаптер магазинов */
    private var gameStoreAdapter: GameStoreAdapter? = null

    /** Адаптер разделов метадаты */
    private var gameMetaDataAdapter: GameMetaDataAdapter? = null

    /** Адаптер оценок метакритики по каждой платформе */
    private var gameMetacriticAdapter: GameMetacriticAdapter? = null

    override fun getLayoutID(): Int = R.layout.fragment_game_page

    override fun parseArguments() {
        super.parseArguments()

        val args: GamePageFragmentArgs by navArgs()
        viewModel.gameId.value = args.gameId
    }

    override fun setUpViews() {
        super.setUpViews()

        gameParentPlatformAdapter = GameParentPlatformAdapter()
        gameScreenshotAdapter = GameScreenshotAdapter()
        gameStoreAdapter = GameStoreAdapter()
        gameMetaDataAdapter = GameMetaDataAdapter()
        gameMetacriticAdapter = GameMetacriticAdapter()

        binding.apply {
            //Действия над ресайклером с родительскими платформами
            rvParentPlatform.apply {
                adapter = gameParentPlatformAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 16, 0))
            }

            ratingBarView = RatingBarView(binding.blockRatings.lRatings)

            //Действия над ресайклером со скриншотами
            blockScreenshots.rvScreenshots.apply {
                adapter = gameScreenshotAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 0, 0))
            }

            //Действия над ресайклером с интернет-магазинами
            rvStores.apply {
                adapter = gameStoreAdapter

                layoutManager = GridLayoutManager(context, STORE_SPAN_COUNT)

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(HorizontalGridItemDecoration(28, 10))
            }

            //Действия над ресайклером с разделами метаданных
            rvMetaData.apply {
                adapter = gameMetaDataAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 0, 0))
            }

            //Действия над ресайклером с оценками игры от метакритики по каждом платформе
            rvMetactiric.apply {
                adapter = gameMetacriticAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(4, 0, 0))
            }
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.gameDetails.observe(viewLifecycleOwner) {
            Log.e("gameDetails", "$it")
            fillGamePage(it)
        }

        viewModel.stores.observe(viewLifecycleOwner) {
            Log.e("stores", "$it")
            if (it.status == Status.SUCCESS && !it.data.isNullOrEmpty()) {
                gameStoreAdapter?.addAll(it.data)
            }
        }

        viewModel.screenshots.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS && it.data != null) {
                //Заполнение скриншотов
                gameScreenshotAdapter?.addAll(it.data)
            }
        }

        viewModel.gameMetaData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS && it.data != null) {
                gameMetaDataAdapter?.addAll(it.data)
            }
        }
    }

    private fun fillGamePage(gameDetails: Resource<GameDetailsModel>) {

        setViewsVisibility(gameDetails)

        if (gameDetails.status == Status.SUCCESS && gameDetails.data != null) {
            val data = gameDetails.data

            //Заполнение родительских платформ
            gameParentPlatformAdapter?.addAll(data.parent_platforms.map { parentPlatform ->
                parentPlatform.image
            })

            gameMetacriticAdapter?.addAll(data.metacritic_platforms)

            binding.apply {
                //Установить картинку игры
                ivBackground.load(data.background_image) {
                    defaultPictureLoadParams(binding.root.context)
                }

                //Установить название игры
                blockTitle.tvTitle.text = data.name

                //Установить дату релиза игры
                binding.blockReleaseDate.tvReleaseDate.text = data.released

                ratingBarView?.setRatings(data.ratingMap)

                //Установить описание игры
                blockAbout.tvAbout.text = data.description_raw

                val tags = if (data.tags.isEmpty()) {
                    null
                } else {
                    data.tags.map {
                        it.name
                    }.reduce { acc, s -> reducedString(acc, s, ", ") }
                }
                tvTags.text = tags
            }
        }
    }

    private fun setViewsVisibility(gameDetails: Resource<GameDetailsModel>) {
        if (gameDetails.status == Status.SUCCESS && gameDetails.data == null) {
            //Данных нет
            return
        }

        val status = gameDetails.status

        binding.apply {
            blockTitle.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockTitle.tvTitle.isVisible = status != Status.LOADING

            blockReleaseDate.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockReleaseDate.clReleaseDate.isVisible = status != Status.LOADING

            blockRatings.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockRatings.lRatings.root.isVisible = status != Status.LOADING

            blockAbout.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockAbout.llAbout.isVisible = status != Status.LOADING

            blockScreenshots.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockScreenshots.rvScreenshots.isVisible = status != Status.LOADING
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        gameParentPlatformAdapter = null
        ratingBarView = null
        gameScreenshotAdapter = null
    }

    companion object {
        private const val TAG = "GamePageFragment"
        private const val STORE_SPAN_COUNT = 2
    }
}