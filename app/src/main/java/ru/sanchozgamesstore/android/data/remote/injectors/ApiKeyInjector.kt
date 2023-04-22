package ru.sanchozgamesstore.android.data.remote.injectors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.sanchozgamesstore.android.data.local.datastore.ApiKeyDataStore
import ru.sanchozgamesstore.android.data.remote.annotations.InjectApiKey

class ApiKeyInjector(
    private val apiKeyDataStore: ApiKeyDataStore,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val annotationPresented = chain.request().isAnnotationPresented(InjectApiKey::class)

        val apiKey = runBlocking {
            return@runBlocking apiKeyDataStore.getApiKey()
        }

        if (!annotationPresented || apiKey == null) {
            return chain.proceed(chain.request())
        }

        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(
                    chain.request().url
                        .newBuilder()
                        .addQueryParameter("key", "$apiKey")
                        .build()
                )
                .build()
        )
    }
}