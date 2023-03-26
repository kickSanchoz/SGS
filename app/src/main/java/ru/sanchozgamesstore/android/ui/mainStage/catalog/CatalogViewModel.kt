package ru.sanchozgamesstore.android.ui.mainStage.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun setAccountToken(token: String) {
        viewModelScope.launch {
            profileRepository.setAccountToken(token)
        }
    }

    fun deleteAccountToken() {
        viewModelScope.launch {
            profileRepository.deleteAccountToken()
        }
    }
}