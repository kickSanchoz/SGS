package ru.sanchozgamesstore.android.ui.splashStage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sanchozgamesstore.android.data.repository.authorization.AuthorizationRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) : ViewModel() {
    val authorizationStatus = liveData<Boolean> {
        emit(authorizationRepository.isAuthorized())
    }
}