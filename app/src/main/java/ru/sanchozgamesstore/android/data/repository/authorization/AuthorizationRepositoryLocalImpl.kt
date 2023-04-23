package ru.sanchozgamesstore.android.data.repository.authorization

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.repository.accountToken.AccountTokenRepository
import javax.inject.Inject

class AuthorizationRepositoryLocalImpl @Inject constructor(
    private val accountTokenRepository: AccountTokenRepository,
) : AuthorizationRepository {
    override suspend fun isAuthorized(): Boolean = withContext(IO) {
        return@withContext accountTokenRepository.hasToken()
    }

    override suspend fun login(
        authorizationModel: UserAuthorizationModel
    ): Resource<Unit> = withContext(IO) {
        accountTokenRepository.setAccountToken("tempToken")

        Resource.success()
    }

    override suspend fun logout(): Resource<Unit> = withContext(IO) {
        accountTokenRepository.deleteAccountToken()

        Resource.success()
    }
}