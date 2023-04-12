package ru.sanchozgamesstore.android.data.domain.models.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.MainApplication

interface SetText {
    fun setText(value: String?)
}

interface Error {
    val isError: Boolean
    fun setError(errorText: String?)
}

abstract class BaseField<T>(private val required: Boolean) : Error {

    val data = MutableLiveData<T>().apply {
        observeForever {
            if (required && validationCondition(it)) {
                val valid = checkValid(it)
                errorText.value = valid.errorText.orEmpty()
            }
        }
    }
    val errorText = MediatorLiveData<String?>().apply {
        observeForever {
            Log.e("errorText", "$it")
        }
    }
    val isErrorEnabled = Transformations.map(errorText) {
        !it.isNullOrEmpty()
    }.apply {
        observeForever {

        }
    }

    override val isError: Boolean
        get() = isErrorEnabled.value == true

    open fun validateField() {
        data.value = data.value?.apply { }
    }

    abstract fun checkValid(value: T): State

    override fun setError(errorText: String?) {
        this.errorText.value = errorText
    }

    open fun validationCondition(value: T): Boolean {
        return value != null
    }

    companion object {
        data class State(val isSuccess: Boolean, val errorText: String? = null) {
            companion object {
                fun success() = State(true, null)
                fun error(text: String) = State(false, text)
                fun error(@StringRes string: Int) =
                    State(false, MainApplication.res.getString(string))
            }
        }
    }
}

abstract class BaseStringField(required: Boolean) : BaseField<String>(required), SetText {

    override fun setText(value: String?) {
        value?.let {
            data.value = it
        }
    }

    override fun checkValid(value: String): Companion.State {
        return if (value.isNotBlank()) {
            Companion.State.success()
        } else {
            Companion.State.error(R.string.required_field)
        }
    }

    override fun validateField() {
        data.value = "".takeIf { data.value == null } ?: data.value.apply { }
    }
}

class StringField(required: Boolean) : BaseStringField(required)