package ru.sanchozgamesstore.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    fun provideContext(
        @ApplicationContext context: ApplicationContext
    ): ApplicationContext = context

    //------------------------Dao------------------------

    //------------------------Dao------------------------


    //------------------------DaoDataSources------------------------

    //------------------------DaoDataSources------------------------
}