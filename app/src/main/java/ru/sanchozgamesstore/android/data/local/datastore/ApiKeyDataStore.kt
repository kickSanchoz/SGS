package ru.sanchozgamesstore.android.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.local.datastore.base.BaseDataStore

class ApiKeyDataStore(appContext: Context) : BaseDataStore(appContext) {

    private val apiKey: Flow<String?> = appDataStore.data.map {
        it[FIELD_API_KEY]?.toString()
    }

    suspend fun getApiKey(): String? = withContext(IO) {
        apiKey.firstOrNull()
    }

    suspend fun setApiKey(apiKey: String) = withContext(IO) {
        appDataStore.edit {
            it[FIELD_API_KEY] = apiKey
        }
    }

    suspend fun deleteApiKey() = withContext(IO) {
        appDataStore.edit {
            it.remove(FIELD_API_KEY)
        }
    }

    private companion object {
        private val FIELD_API_KEY = stringPreferencesKey("API_KEY")
    }
}