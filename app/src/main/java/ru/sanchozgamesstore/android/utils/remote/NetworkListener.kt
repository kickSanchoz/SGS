package ru.sanchozgamesstore.android.utils.remote

import android.content.Context
import android.util.Log
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.Response
import java.io.IOException

class NetworkListener(
    val context: Context
) : EventListener() {

    override fun responseHeadersEnd(call: Call, response: Response) {
        super.responseHeadersEnd(call, response)

        if (response.code !in 200..299) {
            Log.e(TAG, "${response.code}")
        }
    }

    override fun callEnd(call: Call) {
        super.callEnd(call)
    }

    override fun callFailed(call: Call, ioe: IOException) {
        super.callFailed(call, ioe)
    }

    companion object {
        private const val TAG = "NetworkListener"
    }
}