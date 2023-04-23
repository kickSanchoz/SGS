package ru.sanchozgamesstore.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sanchozgamesstore.android.data.local.database.datasources.user.UserDaoDataSource
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import ru.sanchozgamesstore.android.data.local.datastore.ApiKeyDataStore
import ru.sanchozgamesstore.android.data.remote.datasources.authorization.AuthorizationDataSource
import ru.sanchozgamesstore.android.data.remote.datasources.games.GamesDataSource
import ru.sanchozgamesstore.android.data.remote.datasources.profile.ProfileDataSource
import ru.sanchozgamesstore.android.data.repository.accountToken.AccountTokenRepository
import ru.sanchozgamesstore.android.data.repository.accountToken.AccountTokenRepositoryImpl
import ru.sanchozgamesstore.android.data.repository.apiKey.ApiKeyRepository
import ru.sanchozgamesstore.android.data.repository.apiKey.ApiKeyRepositoryImpl
import ru.sanchozgamesstore.android.data.repository.authorization.AuthorizationRepository
import ru.sanchozgamesstore.android.data.repository.authorization.AuthorizationRepositoryImpl
import ru.sanchozgamesstore.android.data.repository.game.GamesRepository
import ru.sanchozgamesstore.android.data.repository.game.GamesRepositoryImpl
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepository
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccountTokenRepository(
        accountTokenDataStore: AccountTokenDataStore,
    ): AccountTokenRepository = AccountTokenRepositoryImpl(
        accountTokenDataStore = accountTokenDataStore,
    )

    @Singleton
    @Provides
    fun provideApiKeyRepository(
        apiKeyDataStore: ApiKeyDataStore,
    ): ApiKeyRepository = ApiKeyRepositoryImpl(
        apiKeyDataStore = apiKeyDataStore,
    )

    @Singleton
    @Provides
    fun provideGameRepository(
        gamesDataSource: GamesDataSource,
    ): GamesRepository = GamesRepositoryImpl(
        gamesDataSource = gamesDataSource,
    )

    @Singleton
    @Provides
    fun provideProfileRepository(
        profileDataSource: ProfileDataSource,
        apiKeyDataStore: ApiKeyDataStore,
        userDaoDataSource: UserDaoDataSource,
    ): ProfileRepository = ProfileRepositoryImpl(
        profileDataSource = profileDataSource,
        apiKeyDataStore = apiKeyDataStore,
        userDaoDataSource = userDaoDataSource,
    )

    @Singleton
    @Provides
    fun provideAuthorizationRepository(
        accountTokenRepository: AccountTokenRepository,
        apiKeyRepository: ApiKeyRepository,
        authorizationDataSource: AuthorizationDataSource,
        profileRepository: ProfileRepository,
    ): AuthorizationRepository = AuthorizationRepositoryImpl(
        accountTokenRepository = accountTokenRepository,
        apiKeyRepository = apiKeyRepository,
        authorizationDataSource = authorizationDataSource,
        profileRepository = profileRepository,
    )
}