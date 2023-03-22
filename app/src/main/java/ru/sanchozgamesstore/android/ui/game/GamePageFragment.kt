package ru.sanchozgamesstore.android.ui.game

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.metadata.GameMetadata
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.platform.MetacriticPlatformModel
import ru.sanchozgamesstore.android.data.domain.models.tag.TagModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status
import ru.sanchozgamesstore.android.ui.customView.RatingBarViewItem
import ru.sanchozgamesstore.android.ui.gallery.dialog.GalleryDialogFragment
import ru.sanchozgamesstore.android.ui.gallery.dialog.GalleryDialogFragment.Companion.getBundle
import ru.sanchozgamesstore.android.ui.game.adapters.*
import ru.sanchozgamesstore.android.ui.game.dialogs.GameDescriptionDialogFragment
import ru.sanchozgamesstore.android.utils.itemDecoration.GridItemDecoration
import ru.sanchozgamesstore.android.utils.itemDecoration.OrientationItemDecoration
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.android.utils.removeItemDecorations
import ru.sanchozgamesstore.android.utils.shimmerEnabled
import ru.sanchozgamesstore.android.utils.toSequence
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
            setCutCount(SCREENSHOTS_CUT_COUNT)
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
                addItemDecoration(
                    GridItemDecoration(
                        spaceBetween_columns = 28,
                        spaceBetween_rows = 10,
                    )
                )
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

        viewModel.screenshots.observe(viewLifecycleOwner) {
            fillScreenshotsBlock(it)
        }

        viewModel.stores.observe(viewLifecycleOwner) {
            Log.d("stores", "$it")
            fillStoresBlock(it)
        }

        viewModel.gameMetaData.observe(viewLifecycleOwner) {
            fillMetadataBlock(it)
        }
    }

    /**
     * Заполнить блоки, зависящие от gameDetails
     * */
    private fun fillGamePage(gameDetails: Resource<GameDetailsModel>) {

        setMainViewsVisibility(gameDetails)

        //Фон страницы игры
        fillBackgroundBlock(gameDetails.map { it?.background_image })

        //Дата выхода
        fillReleaseDateBlock(gameDetails.map { it?.released })

        //Описание игры
        fillAboutBlock(gameDetails.map { it?.description_raw })

        //Оценки метакритики
        fillMetacriticBlock(gameDetails.map { it?.metacritic_platforms })

        //Тэги игры
        fillTagsBlock(gameDetails.map { it?.tags })


        if (gameDetails.dataLoaded) {
            val data = gameDetails.data!!

            //Заполнение родительских платформ
            gameParentPlatformAdapter?.addAll(data.parent_platforms.map { parentPlatform ->
                parentPlatform.image
            })

            binding.apply {
                //Установить название игры
                blockTitle.tvTitle.text = data.name

                //Установить рейтинги, оставленные пользователями
                ratingBarViewItem?.setRatings(data.ratingMap)
            }
        }
    }

    /**
     * Установить видимости вью, зависящих от gameDetails
     * */
    private fun setMainViewsVisibility(gameDetails: Resource<GameDetailsModel>) {
        //TODO заменить данное условие отдельным экраном с ошибкой
        if (gameDetails.dataNotLoaded) {
            //Данных нет
            return
        }

        val status = gameDetails.status

        binding.apply {
            //Родительские платформы
            blockParentPlatform.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockParentPlatform.rvParentPlatform.isVisible = status != Status.LOADING

            //Рейтинг игры
            blockRatings.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockRatings.lRatings.root.isVisible = status != Status.LOADING

            //Название игры
            blockTitle.lShimmer.sflRoot.shimmerEnabled(status == Status.LOADING)
            blockTitle.tvTitle.isVisible = status != Status.LOADING
        }
    }

    /**
     * Заполнение блока фона страницы игры
     * */
    private fun fillBackgroundBlock(background: Resource<String>) {
        binding.apply {
            /*
            * Представление отсутствия данных видимо во всех случаях, кроме успешной загрузке данных
            * */
            blockBackground.lEmpty.root.isVisible = !background.dataLoaded

            /*
            * Фон страницы игры устанавливается только при успешной загрузке данных
            * */
            blockBackground.ivBackground.isVisible = background.dataLoaded

            if (background.dataLoaded) {
                //Установить картинку игры
                blockBackground.ivBackground.apply image@{
                    load(background.data) {
                        pictureLoadParams(this@image)
                    }
                }
            }
        }
    }

    /**
     * Заполнение блока даты выхода
     * */
    private fun fillReleaseDateBlock(releaseDate: Resource<String>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockReleaseDate.lShimmer.sflRoot.shimmerEnabled(releaseDate.status == Status.LOADING)

            /*
            * Блок дата релиза виден, если загрузка завершена
            * */
            blockReleaseDate.clReleaseDate.isVisible = releaseDate.status != Status.LOADING

            /*
            * Карточка даты релиза видна только,
            * если данные загружены и строка не пустая
            * */
            blockReleaseDate.cvReleaseDate.isVisible =
                releaseDate.dataLoaded && releaseDate.data!!.isNotEmpty()

            /*
            * Представление отсутствия данных видимо только,
            * если данные не загружены
            * или
            * если данные загружены и строка пустая
            * */
            blockReleaseDate.lEmpty.root.isVisible =
                releaseDate.dataNotLoaded
                        || releaseDate.dataLoaded && releaseDate.data!!.isEmpty()

            //Установить дату релиза игры
            binding.blockReleaseDate.tvReleaseDate.text = releaseDate.data
        }
    }

    /**
     * Заполнение блока описания игры
     * */
    private fun fillAboutBlock(description: Resource<String>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockAbout.lShimmer.sflRoot.shimmerEnabled(description.status == Status.LOADING)

            /*
            * Блок описания игры виден, если загрузка завершена
            * */
            blockAbout.llAbout.isVisible = description.status != Status.LOADING

            /*
            * Текст и "читать польностью" видно только,
            * если данные загружены и строка не пустая
            * */
            val showContent = description.dataLoaded && description.data!!.isNotEmpty()

            blockAbout.tvAbout.isVisible = showContent
            blockAbout.cvReadFull.isVisible = showContent

            /*
            * Представление отсутствия данных видимо только,
            * если данные не загружены
            * или
            * если данные загружены и строка пустая
            * */
            blockAbout.lEmpty.root.isVisible = description.dataNotLoaded
                    || description.dataLoaded && description.data!!.isEmpty()

            if (description.dataLoaded && description.data!!.isNotEmpty()) {
                //Установить описание игры
                blockAbout.tvAbout.text = description.data

                //Диалоговое окно для удобного чтения описания игры
                blockAbout.llAbout.setOnClickListener {
                    GameDescriptionDialogFragment().apply {
                        arguments = GameDescriptionDialogFragment.getBundle(
                            description = description.data
                        )
                    }.show(childFragmentManager, null)
                }

            }
        }
    }

    /**
     * Заполнение блока оценок метакритики
     * */
    private fun fillMetacriticBlock(metacritics: Resource<List<MetacriticPlatformModel>>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockMetacritic.lShimmer.sflRoot.shimmerEnabled(metacritics.status == Status.LOADING)

            /*
            * Блок оценок метакритики виден, если загрузка завершена
            * */
            blockMetacritic.clMetacritic.isVisible = metacritics.status != Status.LOADING

            /*
            * Ресайклер оценок метакритики виден,
            * если данные загружены и список не пустой
            * */
            blockMetacritic.rvMetactiric.isVisible =
                metacritics.dataLoaded && metacritics.data!!.isNotEmpty()

            /*
            * Представление отсутствия данных видимо только,
            * если данные не загружены
            * или
            * если данные загружены и список пустой
            * */
            blockMetacritic.lEmpty.root.isVisible =
                metacritics.dataNotLoaded
                        || metacritics.dataLoaded && metacritics.data!!.isEmpty()

            if (metacritics.dataLoaded) {
                metacritics.data!!.let { items ->
                    gameMetacriticAdapter?.addAll(items)
                }
            }
        }
    }

    /**
     * Заполнение блока тэгов игры
     * */
    private fun fillTagsBlock(tags: Resource<List<TagModel>>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockTags.lShimmer.sflRoot.shimmerEnabled(tags.status == Status.LOADING)

            /*
            * Блок тэгов виден, если загрузка завершена
            * */
            blockTags.clTags.isVisible = tags.status != Status.LOADING

            /*
            * Последовательность тэгов видна только,
            * если данные загружены и список не пустой
            * */
            blockTags.tvTags.isVisible =
                tags.dataLoaded && tags.data!!.isNotEmpty()

            /*
            * Представление отсутствия данных видимо только,
            * если данные не загружены
            * или
            * если данные загружены и список пустой
            * */
            blockTags.lEmpty.root.isVisible =
                tags.dataNotLoaded
                        || tags.dataLoaded && tags.data!!.isEmpty()

            if (tags.dataLoaded) {
                val tagsSequence = if (tags.data!!.isEmpty()) {
                    null
                } else {
                    tags.data.map {
                        it.name
                    }.toSequence(", ")
                }

                blockTags.tvTags.text = tagsSequence
            }
        }
    }

    /**
     * Заполнение блока скриншотов
     * */
    private fun fillScreenshotsBlock(screenshots: Resource<List<ScreenshotModel>>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockScreenshots.lShimmer.sflRoot.shimmerEnabled(screenshots.status == Status.LOADING)

            /*
            * Ресайклер скриншотов виден только,
            * если данные загружены и список не пустой
            * */
            blockScreenshots.rvScreenshots.isVisible =
                screenshots.dataLoaded && screenshots.data!!.isNotEmpty()

            /*
            * Представление отсутствия данных видимо только,
            * если данные не загружены
            * или
            * если данные загружены и список пустой
            * */
            blockScreenshots.lEmpty.root.isVisible =
                screenshots.dataNotLoaded
                        || screenshots.dataLoaded && screenshots.data!!.isEmpty()
        }

        if (screenshots.dataLoaded) {
            screenshots.data!!.let { items ->
                gameScreenshotAdapter?.apply {
                    //Заполнение скриншотов
                    addAll(items)

                    //Действие при клике на скриншот
                    setOnScreenshotClickListener { pos ->
                        GalleryDialogFragment().apply {
                            arguments = getBundle(
                                galleryItems = items,
                                selectedItem = pos
                            )
                        }.show(childFragmentManager, null)
                    }

                    //Действие при клике на скриншот-декорацию
                    setOnViewAllClickListener {
                        val action =
                            GamePageFragmentDirections.actionGamePageFragmentToGalleryFragment(
                                galleryItems = items.toTypedArray(),
                                title = viewModel.gameTitle.value
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    /**
     * Заполнение блока магазинов
     * */
    private fun fillStoresBlock(stores: Resource<List<GameToStoreModel>>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockStores.lShimmer.sflRoot.shimmerEnabled(stores.status == Status.LOADING)

            /*
            * Блок магазинов виден, если загрузка завершена
            * */
            blockStores.clStores.isVisible = stores.status != Status.LOADING

            /*
            * Ресайклер магазинов виден только,
            * если данные загружены и список не пустой
            * */
            blockStores.rvStores.isVisible =
                stores.dataLoaded && stores.data!!.isNotEmpty()

            /*
            * Представление отсутствия данных видимо только,
            * если данные не загружены
            * или
            * если данные загружены и список пустой
            * */
            blockStores.lEmpty.root.isVisible =
                stores.dataNotLoaded
                        || stores.dataLoaded && stores.data!!.isEmpty()
        }

        if (stores.dataLoaded) {
            stores.data!!.let { data ->
                //Заполнение магазинов
                gameStoreAdapter?.addAll(data)
            }
        }
    }

    /**
     * Заполнение блока метаданных
     * */
    private fun fillMetadataBlock(metadata: Resource<List<GameMetadata>>) {
        binding.apply {
            /*
            * Шиммер виден только на загрузке
            * */
            blockMetadata.lShimmer.sflRoot.shimmerEnabled(metadata.status == Status.LOADING)

            /*
            * Ресайклер метаданных виден, если загрузка завершена
            * */
            blockMetadata.rvMetadata.isVisible = metadata.status != Status.LOADING
        }

        if (metadata.status != Status.LOADING) {
            /*
            * data не может быть null, т.к. всегда устанавливается значение во viewModel, а также
            * представление отсутствия данных отображается внутри адаптера
            * */
            metadata.data!!.let { data ->
                gameMetadataAdapter?.addAll(data)
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
        private const val SCREENSHOTS_CUT_COUNT = 3
    }
}