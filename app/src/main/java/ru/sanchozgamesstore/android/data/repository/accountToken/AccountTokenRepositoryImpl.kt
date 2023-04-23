package ru.sanchozgamesstore.android.data.repository.accountToken

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import javax.inject.Inject

class AccountTokenRepositoryImpl @Inject constructor(
    private val accountTokenDataStore: AccountTokenDataStore,
) : AccountTokenRepository {
    override suspend fun hasToken(): Boolean = withContext(IO) {
        accountTokenDataStore.hasToken()
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