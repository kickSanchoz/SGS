package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status
import ru.sanchozgamesstore.android.ui.customView.RatingBarView
import ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters.GameParentPlatformAdapter
import ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters.GameScreenshotAdapter
import ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters.GameStoreAdapter
import ru.sanchozgamesstore.android.utils.itemDecoration.HorizontalGridItemDecoration
import ru.sanchozgamesstore.android.utils.itemDecoration.OrientationItemDecoration
import ru.sanchozgamesstore.android.utils.removeItemDecorations
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

        binding.apply {
            //Действия над ресайклером с родительскими платформами
            rvParentPlatform.apply {
                adapter = gameParentPlatformAdapter

                //Удалить все декораторы, если они были
                removeItemDecorations()
                addItemDecoration(OrientationItemDecoration(8, 16, 0))
            }

            ratingBarView = RatingBarView(binding.lRatings)

            //Действия над ресайклером со скриншотами
            lScreenshotsSection.rvScreenshots.apply {
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
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.gameDetails.observe(viewLifecycleOwner) {
            Log.e("gameDetails", "$it")

            if (it.status == Status.SUCCESS) {
                it.data?.let { gameInfo ->
                    fillGamePage(gameInfo)
                } ?: run {
                    //Данных нет
                }
            }
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
    }

    private fun fillGamePage(gameInfo: GameDetailsModel) {
        //Заполнение родительских платформ
        gameParentPlatformAdapter?.addAll(gameInfo.parent_platforms.map { parentPlatform ->
            parentPlatform.image
        })

        binding.apply {
            //Установить название игры
            tvTitle.text = gameInfo.name

            //Установить дату релиза игры
            tvReleaseDate.text = gameInfo.released

            ratingBarView?.setRatings(gameInfo.ratingMap)

            //Установить описание игры
            tvAbout.text = gameInfo.description_raw
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