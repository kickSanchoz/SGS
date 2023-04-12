package ru.sanchozgamesstore.android.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun setErrorText(view: TextInputLayout, errorMessage: String?) {
    if (errorMessage.isNullOrBlank()) {
        view.error = null
        view.isErrorEnabled = false
    } else {
        view.error = errorMessage
        view.isErrorEnabled = true
    }
}