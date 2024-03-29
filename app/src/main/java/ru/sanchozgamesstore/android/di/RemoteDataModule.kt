package ru.sanchozgamesstore.android.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.sanchozgamesstore.android.data.remote.datasources.authorization.AuthorizationDataSource
import ru.sanchozgamesstore.android.data.remote.datasources.games.GamesDataSource
import ru.sanchozgamesstore.android.data.remote.datasources.profile.ProfileDataSource
import ru.sanchozgamesstore.android.data.remote.injectors.AccountTokenInjector
import ru.sanchozgamesstore.android.data.remote.injectors.ApiKeyInjector
import ru.sanchozgamesstore.android.data.remote.services.AuthorizationService
import ru.sanchozgamesstore.android.data.remote.services.GamesService
import ru.sanchozgamesstore.android.data.remote.services.ProfileService
import ru.sanchozgamesstore.android.data.repository.accountToken.AccountTokenRepository
import ru.sanchozgamesstore.android.data.repository.apiKey.ApiKeyRepository
import ru.sanchozgamesstore.android.utils.API_URL
import ru.sanchozgamesstore.android.utils.remote.NetworkListener
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(Date().javaClass, Rfc3339DateJsonAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideOkhttp(
        @ApplicationContext applicationContext: Context,
        accountTokenRepository: AccountTokenRepository,
        apiKeyRepository: ApiKeyRepository,
    ): OkHttpClient = OkHttpClient
        .Builder()
        .eventListener(NetworkListener(applicationContext))
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(AccountTokenInjector(accountTokenRepository = accountTokenRepository))
        .addInterceptor(ApiKeyInjector(apiKeyRepository = apiKeyRepository))
        .build()


    //------------------------API Services------------------------

    @Provides
    fun provideAuthorizationService(retrofit: Retrofit): AuthorizationService =
        retrofit.create(AuthorizationService::class.java)

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Provides
    fun provideGamesService(retrofit: Retrofit): GamesService =
        retrofit.create(GamesService::class.java)

    //------------------------API Services------------------------


    //------------------------DataSources------------------------

    @Singleton
    @Provides
    fun provideAuthorizationDataSource(
        authorizationService: AuthorizationService,
    ) = AuthorizationDataSource(
        authorizationService = authorizationService,
    )

    @Singleton
    @Provides
    fun provideProfileDataSource(
        profileService: ProfileService,
    ) = ProfileDataSource(
        profileService = profileService,
    )

    @Singleton
    @Provides
    fun provideGamesDataSource(
        gamesService: GamesService,
    ) = GamesDataSource(
        gamesService = gamesService,
    )

    //------------------------DataSources------------------------
}
