package ru.sanchozgamesstore.android.data.repository.authorization

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.domain.models.user.UserAuthorizationModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import javax.inject.Inject

class AuthorizationRepositoryLocalImpl @Inject constructor(
    private val accountTokenDataStore: AccountTokenDataStore,
) : AuthorizationRepository {
    override suspend fun isAuthorized(): Boolean = withContext(IO) {
        return@withContext accountTokenDataStore.isAuthorized()
    }

    override suspend fun login(
        authorizationModel: UserAuthorizationModel
    ): Resource<Unit> = withContext(IO) {
        setAccountToken("tempToken")

        Resource.success()
    }

    override suspend fun logout(): Resource<Unit> = withContext(IO) {
        deleteAccountToken()

        Resource.success()
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