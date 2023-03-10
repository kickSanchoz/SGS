package ru.sanchozgamesstore.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.sanchozgamesstore.android.data.repository.game.GameRepository
import ru.sanchozgamesstore.android.data.repository.game.GameRepositoryLocalImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGameRepository(): GameRepository = GameRepositoryLocalImpl()
}