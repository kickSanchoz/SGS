package ru.sanchozgamesstore.android.data.repository.authorization

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.remote.datasources.authorization.AuthorizationDataSource
import ru.sanchozgamesstore.android.data.repository.accountToken.AccountTokenRepository
import ru.sanchozgamesstore.android.data.repository.apiKey.ApiKeyRepository
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepository
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val accountTokenRepository: AccountTokenRepository,
    private val apiKeyRepository: ApiKeyRepository,
    private val authorizationDataSource: AuthorizationDataSource,
    private val profileRepository: ProfileRepository,
) : AuthorizationRepository {
    override suspend fun isAuthorized(): Boolean = withContext(IO) {
        return@withContext accountTokenRepository.hasToken()
    }

    override suspend fun login(
        authorizationModel: UserAuthorizationModel
    ): Resource<Unit> =
        withContext(IO) {
            val res = authorizationDataSource.login(authorizationModel)

            if (res.dataLoaded) {
                accountTokenRepository.setAccountToken(res.data!!)
                profileRepository.fetchProfile()
            }

            return@withContext res.map { }
        }

    override suspend fun logout(): Resource<Unit> = withContext(IO) {
        accountTokenRepository.deleteAccountToken()
        apiKeyRepository.deleteApiKey()
        profileRepository.clearProfileLocalData()

        return@withContext Resource.success()
    }
}