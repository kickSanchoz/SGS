package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.enums.GameMetadataSection
import ru.sanchozgamesstore.android.data.domain.models.developer.DeveloperModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GamePlatformModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.metadata.GameMetadata
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.genre.GenreModel
import ru.sanchozgamesstore.android.data.domain.models.publisher.PublisherModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.game.GameRepository
import ru.sanchozgamesstore.android.utils.reducedString
import javax.inject.Inject

@HiltViewModel
class GamePageViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    /**
     * Костылик :)
     *
     * Сохранённый id игры, для проверки при перезаходе на фрагмент (чтобы заново не вызывать методы)
     * */
    private var savedGameId: Int? = null

    /**
     * Id игры
     * */
    val gameId = MutableLiveData<Int>().apply {
        observeForever {
            if (it != savedGameId) {
                savedGameId = it
                getGameDetails(it)
            }
        }
    }

    /**
     * Вся детальная информация по игре
     * */
    val gameDetails = MutableLiveData<Resource<GameDetailsModel>>().apply {
        value = Resource.loading()

        observeForever {
            if (it.status == Resource.Status.SUCCESS && it.data != null) {
                getGameStores(it.data.id, it.data.stores)
                getGameScreenshots(it.data.id)
            }
        }
    }

    /**
     * Название игры
     * */
    val gameTitle = gameDetails.map {
        it.data?.name
    }.apply {
        observeForever { }
    }

    /**
     * Метаданные игры
     * */
    val gameMetaData = gameDetails.map { details ->
        val data = details.data

        val res = if (details.status == Resource.Status.SUCCESS && data != null) {
            listOf<GameMetadata>(
                GameMetadata(
                    header = GameMetadataSection.PLATFORMS,
                    sequence = platformsToSequence(data.platforms)
                ),
                GameMetadata(
                    header = GameMetadataSection.GENRES,
                    sequence = genresToSequence(data.genres)
                ),
                GameMetadata(
                    header = GameMetadataSection.DEVELOPERS,
                    sequence = developersToSequence(data.developers)
                ),
                GameMetadata(
                    header = GameMetadataSection.PUBLISHERS,
                    sequence = publishersToSequence(data.publishers)
                ),
            )
        } else {
            null
        }

        Resource(details.status, res, null)
    }

    /**
     * Список интернет-магазинов
     * */
    val stores = MutableLiveData<Resource<List<GameToStoreModel>>>().apply {
        value = Resource.loading()
    }

    /**
     * Список скриншотов игры
     * */
    val screenshots = MutableLiveData<Resource<List<ScreenshotModel>>>().apply {
        value = Resource.loading()
    }

    //---------------------------------Получение данных---------------------------------
    /**
     * Получить детальную информацию по игре
     * */
    private fun getGameDetails(id: Int) {
        gameDetails.value = Resource.loading()

        viewModelScope.launch {
            gameDetails.value = gameRepository.getGameDetail(id)
        }
    }

    /**
     * Получить магазины, на которых продаётся игра (БЕЗ НАЗВАНИЙ, ТОЛЬКО ССЫЛКИ НА СТРАНИЦЫ МАГАЗИНОВ)
     * */
    fun getGameStores(id: Int) {
        stores.value = Resource.loading()

        viewModelScope.launch {
            stores.value = gameRepository.getGameStores(id)
        }
    }

    /**
     * Получить магазины, на которых продаётся игра (С НАЗВАНИЕМ, ДОМЕНАМИ И ССЫЛКАМИ НА СТРАНИЦУ МАГАЗИНА)
     * */
    private fun getGameStores(id: Int, remoteStores: List<StoreModel>) {
        stores.value = Resource.loading()

        viewModelScope.launch {
            stores.value = gameRepository.getGameStores(id, remoteStores)
        }
    }

    /**
     * Получить все скриншоты игры
     * */
    private fun getGameScreenshots(id: Int) {
        screenshots.value = Resource.loading()

        viewModelScope.launch {
            screenshots.value = gameRepository.getGameScreenshots(id)
        }
    }
    //---------------------------------Получение данных---------------------------------


    //---------------------------------Доп. методы---------------------------------

    /**
     * Список строк в последовательность
     *
     * @return null, если пустой список, иначе преобразованную строку
     * */
    private fun List<String>.toSequence(): String? {
        return if (this.isEmpty()) {
            null
        } else {
            this.reduce { acc, s -> reducedString(acc, s, DELIMITER_NEWLINE) }
        }
    }

    /**
     * Преобразовать игровые плафтормы к виду
     *
     * (
     *  "Название раздела",
     *  "список платформ в виде строки"
     * )
     *
     * @param platforms список игровых платформ
     * */
    private fun platformsToSequence(
        platforms: List<GamePlatformModel>,
    ): String? {
        val res = platforms.map {
            it.platform.name
        }.toSequence()

        return res
    }

    /**
     * Преобразовать жанры игры к виду
     *
     * (
     *  "Название раздела",
     *  "список жанров в виде строки"
     * )
     *
     * @param genres список жанров
     * */
    private fun genresToSequence(
        genres: List<GenreModel>,
    ): String? {
        val res = genres.map {
            it.name
        }.toSequence()

        return res
    }

    /**
     * Преобразовать разработчиков игры к виду
     *
     * (
     *  "Название раздела",
     *  "список разработчиков в виде строки"
     * )
     *
     * @param developers список разработчиков
     * */
    private fun developersToSequence(
        developers: List<DeveloperModel>,
    ): String? {
        val res = developers.map {
            it.name
        }.toSequence()

        return res
    }

    /**
     * Преобразовать издателей игры к виду
     *
     * (
     *  "Название раздела",
     *  "список издателей в виде строки"
     * )
     *
     * @param publishers список издателей
     * */
    private fun publishersToSequence(
        publishers: List<PublisherModel>,
    ): String? {
        val res = publishers.map {
            it.name
        }.toSequence()

        return res
    }

    //---------------------------------Доп. методы---------------------------------

    override fun onCleared() {
        super.onCleared()
        Log.e("ViewModel", "onCleared: ")
    }

    companion object {
        private const val DELIMITER_NEWLINE = ",\n"
    }

}