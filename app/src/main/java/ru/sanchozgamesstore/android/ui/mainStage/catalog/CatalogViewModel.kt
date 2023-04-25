package ru.sanchozgamesstore.android.ui.mainStage.catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sanchozgamesstore.android.data.repository.game.GamesRepository
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : ViewModel() {

    /**
     * Поисковая строка
     * */
    val searchQuery = MutableLiveData<String>("")

    private var lastSearchQuery: String? = null

    private val refreshTrigger = MutableLiveData(Unit)

    /**
     * Выполнить поиск игр по запросу
     * */
    fun performSearch() {
        if (searchQuery.value != lastSearchQuery) {
            refreshTrigger.value = refreshTrigger.value.apply { }
        }
    }

    /**
     * Список игр
     * */
    val games = refreshTrigger.switchMap {
        lastSearchQuery = searchQuery.value
        gamesRepository.getGamesBySearch(searchQuery.value ?: "")
    }
}