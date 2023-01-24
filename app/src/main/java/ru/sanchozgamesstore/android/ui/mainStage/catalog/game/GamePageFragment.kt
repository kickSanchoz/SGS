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
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status
import ru.sanchozgamesstore.android.ui.customView.RatingBarViewItem
import ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters.*
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.android.utils.itemDecoration.OrientationItemDecoration
import ru.sanchozgamesstore.android.utils.itemDecoration.VerticalGridItemDecoration
import ru.sanchozgamesstore.android.utils.reducedString
import ru.sanchozgamesstore.android.utils.removeItemDecorations
import ru.sanchozgamesstore.android.utils.shimmerEnabled
import ru.sanchozgamesstore.databinding.FragmentGamePageBinding

@AndroidEntryPoint
class GamePageFragment : BaseFragment<FragmentGamePageBinding>() {

    private val viewModel: GamePageViewModel by viewModels()

    /** Кастомное представление полоски рейтинга */
    private var ratingBarViewItem: RatingBarViewItem? = null

    /** Адаптер родительских платформ */
    private var gameParentPlatformAdapter: GameParentPlatformAdapter? = null

    /** Адаптер скриншотов */
    private var gameScreenshotAdapter: GameScreenshotAdapter? = null

    /** Адаптер магазинов */
    private var gameStoreAdapter: GameStoreAdapter? = null

    /** Адаптер разделов метадаты */
    private var gameMetadataAdapter: GameMetadataAdapter? = null

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
        gameScreenshotAdapter = GameScreenshotAdapter().apply {
            setCutCount(3)
        }
        gameStoreAdapter = GameStoreAdapter()
        gameMetadataAdapter = GameMetadataAdapter()
        gameMetacriticAdapter = GameMetacriticAdapter()

        binding.apply {
            //Действия над ресайклером с родительскими платформами
            blockParentPlatform.rvParentPlatform.apply {
                adapter = gameParentPlatformAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 16, 0))
            }

            ratingBarViewItem = RatingBarViewItem(binding.blockRatings.lRatings)

            //Действия над ресайклером со скриншотами
            blockScreenshots.rvScreenshots.apply {
                adapter = gameScreenshotAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 0, 0))
            }

            //Действия над ресайклером с интернет-магазинами
            blockStores.rvStores.apply {
                adapter = gameStoreAdapter

                layoutManager = GridLayoutManager(context, STORE_SPAN_COUNT)

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(VerticalGridItemDecoration(28, 10))
            }

            //Действия над ресайклером с оценками игры от метакритики по каждом платформе
            blockMetacritic.rvMetactiric.apply {
                adapter = gameMetacriticAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(4, 0, 0))
            }

            //Действия над ресайклером с разделами метаданных
            blockMetadata.rvMetadata.apply {
                adapter = gameMetadataAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 0, 0))
            }
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.gameDetails.observe(viewLifecycleOwner) {
            Log.d("gameDetails", "$it")
            fillGamePage(it)
        }

        viewModel.stores.observe(viewLifecycleOwner) {
            Log.d("stores", "$it")
            fillStoresBlock(it)
        }

        viewModel.screenshots.observe(viewLifecycleOwner) {
            fillScreenshotsBlock(it)
        }

        viewModel.gameMetaData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS && it.data != null) {
                gameMetadataAdapter?.addAll(it.data)
            }
        }
    }

    /**
     * Заполнить блоки, зависящие от gameDetails
     * */
    private fun fillGamePage(gameDetails: Resource<GameDetailsModel>) {

        setMainViewsVisibility(gameDetails)

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

                ratingBarViewItem?.setRatings(data.ratingMap)

                //Установить описание игры
                blockAbout.tvAbout.text = data.description_raw

                val tags = if (data.tags.isEmpty()) {
                    null
                } else {
                    data.tags.map {
                        it.name
                    }.reduce { acc, s -> reducedString(acc, s, ", ") }
                }
                blockTags.tvTags.text = tags
            }
        }
    }

    /**
     * Установить видимости вью, зависящих от gameDetails
     * */
    private fun setMainViewsVisibility(gameDetails: Resource<GameDetailsModel>) {
        if (gameDetails.status == Status.SUCCESS && gameDetails.data == null) {
            //Данных нет
            return
        }

        val status = gameDetails.status

        binding.apply {
            blockParentPlatform.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockParentPlatform.rvParentPlatform.isVisible = status != Status.LOADING

            blockTitle.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockTitle.tvTitle.isVisible = status != Status.LOADING

            blockReleaseDate.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockReleaseDate.clReleaseDate.isVisible = status != Status.LOADING

            blockRatings.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockRatings.lRatings.root.isVisible = status != Status.LOADING

            blockAbout.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockAbout.llAbout.isVisible = status != Status.LOADING

            blockMetacritic.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockMetacritic.llMetacritic.isVisible = status != Status.LOADING

            blockMetadata.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockMetadata.rvMetadata.isVisible = status != Status.LOADING

            blockTags.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockTags.llTags.isVisible = status != Status.LOADING
        }
    }

    /**
     * Заполнение блока скриншотов
     * */
    private fun fillScreenshotsBlock(screenshots: Resource<List<ScreenshotModel>>) {

        val status = screenshots.status

        binding.apply {
            blockScreenshots.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockScreenshots.rvScreenshots.isVisible = status != Status.LOADING
        }

        if (screenshots.status == Status.SUCCESS) {
            screenshots.data?.let { data ->
                //Заполнение скриншотов
                gameScreenshotAdapter?.addAll(data)
            } ?: run {
                //Данных нет
                return
            }
        }
    }

    /**
     * Заполнение блока магазинов
     * */
    private fun fillStoresBlock(stores: Resource<List<GameToStoreModel>>) {
        val status = stores.status

        binding.apply {
            blockStores.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockStores.llStores.isVisible = status != Status.LOADING
        }

        if (stores.status == Status.SUCCESS) {
            stores.data?.let { data ->
                //Заполнение магазинов
                gameStoreAdapter?.addAll(data)
            } ?: run {
                //Данных нет
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        gameParentPlatformAdapter = null
        ratingBarViewItem = null
        gameScreenshotAdapter = null
        gameStoreAdapter = null
        gameMetacriticAdapter = null
        gameMetadataAdapter = null
    }

    companion object {
        private const val TAG = "GamePageFragment"
        private const val STORE_SPAN_COUNT = 2
    }
}