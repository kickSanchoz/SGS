package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.models.GameStoreModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.game.GameRepository
import javax.inject.Inject

@HiltViewModel
class GamePageViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val gameId = MutableLiveData<Int>()

    val stores = MutableLiveData<Resource<List<GameStoreModel>>>().apply {
        value = Resource.loading()
    }

    fun getGameStores(id: Int) {
        stores.value = Resource.loading()

        viewModelScope.launch {
            stores.value = gameRepository.getGameStores(id, emptyList())
        }
    }
}