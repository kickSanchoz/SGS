package ru.sanchozgamesstore.android.data.repository.apiKey

interface ApiKeyRepository {
    /**
     * Получить апи ключ для взаимодействия с сервисом
     * */
    suspend fun getApiKey(): String?

    /**
     * Установить апи ключ сервиса
     * */
    suspend fun setApiKey(apiKey: String): Unit

    /**
     * Удалить апи ключ сервиса из локального хранилища
     * */
    suspend fun deleteApiKey(): Unit
}