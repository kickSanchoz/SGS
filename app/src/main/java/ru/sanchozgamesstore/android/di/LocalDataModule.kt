package ru.sanchozgamesstore.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sanchozgamesstore.android.data.local.database.AppDatabase
import ru.sanchozgamesstore.android.data.local.database.dao.UserDao
import ru.sanchozgamesstore.android.data.local.database.datasources.user.UserDaoDataSource
import ru.sanchozgamesstore.android.data.local.datastore.AccountTokenDataStore
import ru.sanchozgamesstore.android.data.local.datastore.ApiKeyDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context = context

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    //------------------------DataStore------------------------

    @Provides
    @Singleton
    fun provideAccountTokenDataStore(
        @ApplicationContext context: Context
    ): AccountTokenDataStore = AccountTokenDataStore(
        appContext = context
    )

    @Provides
    @Singleton
    fun provideApiKeyDataStore(
        @ApplicationContext context: Context
    ): ApiKeyDataStore = ApiKeyDataStore(
        appContext = context
    )

    //------------------------DataStore------------------------


    //------------------------Dao------------------------

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    //------------------------Dao------------------------


    //------------------------DaoDataSources------------------------

    @Singleton
    @Provides
    fun provideUserDaoDataSource(
        userDao: UserDao,
    ): UserDaoDataSource = UserDaoDataSource(
        userDao = userDao,
    )

    //------------------------DaoDataSources------------------------
}