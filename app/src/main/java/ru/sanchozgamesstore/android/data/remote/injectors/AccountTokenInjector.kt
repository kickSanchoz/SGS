package ru.sanchozgamesstore.android.data.remote.injectors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.sanchozgamesstore.android.data.remote.annotations.InjectAccountToken
import ru.sanchozgamesstore.android.data.repository.accountToken.AccountTokenRepository

class AccountTokenInjector(
    private val accountTokenRepository: AccountTokenRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val annotationPresented = chain.request().isAnnotationPresented(InjectAccountToken::class)

        val accountToken = runBlocking {
            return@runBlocking accountTokenRepository.getAccountToken()
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