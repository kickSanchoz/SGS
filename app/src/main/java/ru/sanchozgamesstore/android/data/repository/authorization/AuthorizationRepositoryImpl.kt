package ru.sanchozgamesstore.android.data.repository.authorization

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import ru.sanchozgamesstore.android.data.remote.datasources.authorization.AuthorizationDataSource
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val accountTokenDataStore: AccountTokenDataStore,
    private val authorizationDataSource: AuthorizationDataSource,
) : AuthorizationRepository {
    override suspend fun isAuthorized(): Boolean = withContext(IO) {
        return@withContext accountTokenDataStore.isAuthorized()
    }

    override suspend fun login(
        authorizationModel: UserAuthorizationModel
    ): Resource<Unit> =
        withContext(IO) {
            val res = authorizationDataSource.login(authorizationModel)

            if (res.dataLoaded) {
                setAccountToken(res.data!!)
            }

            return@withContext res.map { }
        }

    override suspend fun logout(): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountToken(): String? = withContext(IO) {
        accountTokenDataStore.getAccountToken()
    }

    override suspend fun getAccountTokenLiveData(): LiveData<String?> =
        accountTokenDataStore.getAccountTokenLiveData()


    override suspend fun setAccountToken(token: String): Unit = withContext(IO) {
        accountTokenDataStore.setAccountToken(token)
    }

    override suspend fun deleteAccountToken(): Unit = withContext(IO) {
        accountTokenDataStore.deleteAccountToken()
    }
}