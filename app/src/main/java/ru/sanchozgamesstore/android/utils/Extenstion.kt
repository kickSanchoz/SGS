package ru.sanchozgamesstore.android.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.sanchozgamesstore.android.ui.mainStage.MainActivity

fun Activity.toMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
}

fun Fragment.toMainActivity() {
    activity?.toMainActivity()
}