package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.game.screnshot.ScreenshotModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.game.GameRepository
import javax.inject.Inject

@HiltViewModel
class GamePageViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val gameId = MutableLiveData<Int>().apply {
        observeForever {
            getGameDetails(it)
        }
    }

    val gameDetails = MutableLiveData<Resource<GameDetailsModel>>().apply {
        value = Resource.loading()

        observeForever {
            if (it.status == Resource.Status.SUCCESS && it.data != null) {
                getGameStores(it.data.id, it.data.stores)
                getGameScreenshots(it.data.id)
            }
        }
    }

    val stores = MutableLiveData<Resource<List<GameToStoreModel>>>().apply {
        value = Resource.loading()
    }

    val screenshots = MutableLiveData<Resource<List<ScreenshotModel>>>().apply {
        value = Resource.loading()
    }

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
}