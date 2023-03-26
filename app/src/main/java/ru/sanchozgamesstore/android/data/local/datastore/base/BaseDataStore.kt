package ru.sanchozgamesstore.android.data.local.datastore.base

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ru.sanchozgamesstore.android.utils.DATASTORE_NAME

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

abstract class BaseDataStore(appContext: Context) {

    protected val appDataStore = appContext.dataStore
}