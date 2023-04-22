package ru.sanchozgamesstore.android.data.remote.injectors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import ru.sanchozgamesstore.android.data.remote.annotations.InjectAccountToken

class AccountTokenInjector(
    private val accountTokenDataStore: AccountTokenDataStore,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val annotationPresented = chain.request().isAnnotationPresented(InjectAccountToken::class)

        val accountToken = runBlocking {
            return@runBlocking accountTokenDataStore.getAccountToken()
        }


        if (!annotationPresented || accountToken == null) {
            return chain.proceed(chain.request())
        }

        return chain.proceed(
            chain.request()
                .newBuilder()
                .header("Token", "token $accountToken")
                .build()
        )
    }
}