package ru.sanchozgamesstore.android.ui.authorizationStage.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.authorization.AuthorizationRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) : ViewModel() {

    val authorizationModel = UserAuthorizationModel()

    val loginState = MutableLiveData<Resource<Unit>>()

    fun login() {
        loginState.value = Resource.loading()
        viewModelScope.launch {
            if (authorizationModel.isAuthorizationFieldsIsFilled()) {
                val res = authorizationRepository.login(authorizationModel)
                loginState.value = res
            }
        }
    }
}