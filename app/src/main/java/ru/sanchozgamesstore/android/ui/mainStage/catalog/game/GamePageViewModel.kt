package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.data.domain.models.store.StoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.game.GameRepository
import javax.inject.Inject

@HiltViewModel
class GamePageViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val gameId = MutableLiveData<Int>()

    val gameDetails = MutableLiveData<Resource<GameDetailsModel>>().apply {
        value = Resource.loading()
    }

    val stores = MutableLiveData<Resource<List<GameToStoreModel>>>().apply {
        value = Resource.loading()
    }

    fun getGameDetails(id: Int) {
        gameDetails.value = Resource.loading()

        viewModelScope.launch {
            gameDetails.value = gameRepository.getGameDetail(id)
        }
    }

    fun getGameStores(id: Int) {
        stores.value = Resource.loading()

        viewModelScope.launch {
            stores.value = gameRepository.getGameStores(id)
        }
    }

    fun getGameStores(id: Int, remoteStores: List<StoreModel>) {
        stores.value = Resource.loading()

        viewModelScope.launch {
            stores.value = gameRepository.getGameStores(id, remoteStores)
        }
    }
}