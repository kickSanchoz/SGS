package ru.sanchozgamesstore.android.ui.mainStage.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.authorization.AuthorizationRepository
import ru.sanchozgamesstore.android.data.repository.game.GamesRepository
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
    private val profileRepository: ProfileRepository,
    private val gamesRepository: GamesRepository,
) : ViewModel() {

    private val refreshTrigger = MutableLiveData(Unit)

    /**
     * Обновить страницу
     * */
    fun refreshPage() {
        refreshTrigger.value = refreshTrigger.value.apply { }
    }

    /**
     * Данные профиля
     * */
    val profile = refreshTrigger.switchMap {
        liveData {
            emit(Resource.loading())
            emitSource(profileRepository.getProfileLiveData())
        }
    }

    /**
     * Избранные игры
     * */
    val favoriteGames = refreshTrigger.switchMap {
        liveData {
            emit(Resource.loading())
            emit(gamesRepository.getFavoriteGames())
        }
    }

    val logoutStatus = MutableLiveData<Resource<Unit>>()

    fun logout() {
        viewModelScope.launch {
            val res = authorizationRepository.logout()
            logoutStatus.value = res
        }
    }
}