package ru.sanchozgamesstore.android.base

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import retrofit2.Response
import ru.sanchozgamesstore.android.data.domain.response.ErrorBody
import ru.sanchozgamesstore.android.data.domain.response.Resource
import java.io.IOException

abstract class BaseDataSource {

    private val adapter: JsonAdapter<ErrorBody> =
        Moshi.Builder().build().adapter(ErrorBody::class.java)

    @Suppress("BlockingMethodInNonBlockingContext")
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            return if (response.isSuccessful) {
                val body = response.body()
                Resource.success(body)
            } else {
                val errorBody = adapter.fromJson(response.errorBody()!!.byteString().utf8())
                Log.e("Retrofit", "Url: ${response.raw().request.url} ")
                Log.e("Retrofit", "Method: ${response.raw().request.method} ")
                Log.e("Retrofit", "=============================")
                Log.e("Retrofit", errorBody.toString())
                var errorString = ""
                errorBody?.errors?.forEach { (t, u) ->
                    errorString += "$t: ${u.reduceOrNull { acc, s -> "$acc, $s" }}\n"
                }
                Log.e("Retrofit", "Errors: $errorString")
                Log.e("Retrofit", "httpCode: ${response.code()}")
                Log.e("Retrofit", "=============================")

                Resource.error(errorBody!!) // need test
            }
        } catch (e: JsonDataException) {
            e.printStackTrace()
            return Resource.error(ErrorBody(e.message ?: e.toString())) // сообщение о ошибке
        } catch (e: IOException) {
            e.printStackTrace()
            return Resource.networkError(ErrorBody(e.message ?: e.toString()))
        }
    }
}