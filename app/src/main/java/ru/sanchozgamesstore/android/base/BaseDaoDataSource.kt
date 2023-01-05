package ru.sanchozgamesstore.android.base

import android.database.sqlite.SQLiteException
import ru.sanchozgamesstore.android.data.domain.response.Resource

abstract class BaseDaoDataSource {

    protected suspend fun <T> getResultAsync(call: suspend () -> T): Resource<T> {
        try {
            val response = call()
            return Resource.success(response)
        } catch (e: SQLiteException) {
            e.printStackTrace()
            return Resource.error("$e")
        }
    }

    protected fun <T> getResult(call: () -> T): T {
        try {
            val response = call()
            return response
        } catch (e: SQLiteException) {
            e.printStackTrace()
            throw SQLiteException(e.message ?: e.toString())
        }
    }
}