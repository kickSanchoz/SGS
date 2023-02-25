package ru.sanchozgamesstore.android.ui.mainStage.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    /**
     * Данные профиля
     * */
    val profile = MutableLiveData<Resource<UserModel>>().apply {
        value = Resource.loading()
    }

    /**
     * Получить данные профиля
     * */
    private fun getProfile() {
        profile.value = Resource.loading()

        viewModelScope.launch {
            val res = profileRepository.getProfile()
            profile.value = res
        }
    }

    /**
     * Обновить страницу
     * */
    fun refreshPage() {
        getProfile()
    }

    init {
        getProfile()
    }
}