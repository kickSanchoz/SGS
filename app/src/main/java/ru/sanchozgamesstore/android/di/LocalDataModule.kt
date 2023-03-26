package ru.sanchozgamesstore.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context = context

    //------------------------DataStore------------------------

    @Provides
    @Singleton
    fun provideAccountTokenDataStore(
        @ApplicationContext context: Context
    ): AccountTokenDataStore = AccountTokenDataStore(
        appContext = context
    )

    //------------------------DataStore------------------------


    //------------------------Dao------------------------

    //------------------------Dao------------------------


    //------------------------DaoDataSources------------------------

    //------------------------DaoDataSources------------------------
}