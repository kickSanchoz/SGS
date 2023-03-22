package ru.sanchozgamesstore.android.utils

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DeepLinkController(fragment: Fragment) {

    private val navController = fragment.findNavController()

    private val baseUri = "sgs://"

    /**
     * Открыть страницу игру
     *
     * @param id идентификатор игры
     * */
    fun openGamePageFragment(id: Int) {
        navController.navigate(Uri.parse(baseUri+"gamePageFragment/$id"))
    }
}