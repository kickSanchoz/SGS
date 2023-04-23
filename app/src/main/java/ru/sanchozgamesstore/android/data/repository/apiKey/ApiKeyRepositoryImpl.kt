package ru.sanchozgamesstore.android.data.repository.apiKey

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.local.datastore.ApiKeyDataStore
import javax.inject.Inject

class ApiKeyRepositoryImpl @Inject constructor(
    private val apiKeyDataStore: ApiKeyDataStore,
): ApiKeyRepository {
    override suspend fun getApiKey(): String? = withContext(IO) {
        apiKeyDataStore.getApiKey()
    }

    override suspend fun setApiKey(apiKey: String): Unit = withContext(IO) {
        apiKeyDataStore.setApiKey(apiKey)
    }

    override suspend fun deleteApiKey(): Unit = withContext(IO) {
        apiKeyDataStore.deleteApiKey()
    }
}