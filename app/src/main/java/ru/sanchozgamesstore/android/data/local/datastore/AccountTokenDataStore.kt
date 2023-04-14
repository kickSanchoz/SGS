package ru.sanchozgamesstore.android.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.sanchozgamesstore.android.data.local.datastore.base.BaseDataStore

class AccountTokenDataStore(appContext: Context) : BaseDataStore(appContext) {

    /**
     * Токен пользователя
     * */
    private val accountToken: Flow<String?> = appDataStore.data.map {
        it[FIELD_TOKEN]?.toString()
    }

    /**
     * Получить токен аккаунта в виде liveData, если он есть, иначе null
     * */
    suspend fun getAccountTokenLiveData(): LiveData<String?> = withContext(IO) {
        accountToken.asLiveData()
    }

    /**
     * Получить токен аккаунта, если он есть, иначе null
     * */
    suspend fun getAccountToken(): String? = withContext(IO) {
        accountToken.firstOrNull()
    }

    /**
     * Пользователь авторизован
     * */
    suspend fun isAuthorized(): Boolean = withContext(IO) {
        val res = accountToken.firstOrNull() != null
        Log.e("token exist", "$res")
        return@withContext res
    }

    /**
     * Установить токен аккаунта
     * */
    suspend fun setAccountToken(token: String) = withContext(IO) {
        appDataStore.edit {
            it[FIELD_TOKEN] = token
        }
    }

    /**
     * Удалить токен аккаунта
     * */
    suspend fun deleteAccountToken() = withContext(IO) {
        appDataStore.edit {
            Log.e("removing", "yep")
            it.remove(FIELD_TOKEN)
        }
    }

    private companion object {
        private val FIELD_TOKEN = stringPreferencesKey("ACCOUNT_TOKEN")
    }
}