package ru.sanchozgamesstore.android.data.remote.injectors

import okhttp3.Request
import retrofit2.Invocation
import kotlin.reflect.KClass

internal fun Request.isAnnotationPresented(annotation: KClass<out Annotation>): Boolean{
    return tag(Invocation::class.java)
        ?.method()
        ?.isAnnotationPresent(annotation.java) ?: false
}