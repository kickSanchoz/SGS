package ru.sanchozgamesstore.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import ru.sanchozgamesstore.android.data.repository.game.GameRepository
import ru.sanchozgamesstore.android.data.repository.game.GameRepositoryLocalImpl
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepository
import ru.sanchozgamesstore.android.data.repository.profile.ProfileRepositoryLocalImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGameRepository(): GameRepository = GameRepositoryLocalImpl()

    @Singleton
    @Provides
    fun provideProfileRepository(
        accountTokenDataStore: AccountTokenDataStore
    ): ProfileRepository = ProfileRepositoryLocalImpl(
        accountTokenDataStore = accountTokenDataStore
    )
}