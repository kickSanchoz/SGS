package ru.sanchozgamesstore.android.data.remote.datasources.authorization

import ru.sanchozgamesstore.android.base.BaseDataSource
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.models.user.toAuthorizationApiModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.services.AuthorizationService
import javax.inject.Inject

class AuthorizationDataSource @Inject constructor(
    private val authorizationService: AuthorizationService,
) : BaseDataSource() {
    suspend fun login(
        userAuthorizationModel: UserAuthorizationModel
    ): Resource<String> = getResult {
        authorizationService.login(
            userAuthorizationModel.toAuthorizationApiModel()
        )
    }.map {
        it?.key
    }
}