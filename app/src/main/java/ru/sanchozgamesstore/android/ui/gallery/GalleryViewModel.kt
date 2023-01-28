package ru.sanchozgamesstore.android.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.sanchozgamesstore.android.data.domain.models.game.screenshot.ScreenshotModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() : ViewModel() {

    /**
     * Заголовок галереи
     * */
    var title: String? = null

    /**
     * Список элементов галереи
     * */
    val galleryItems = MutableLiveData<List<ScreenshotModel>>()

    /**
     * Позиция отображаемого элемента в диалоговом окне галереи
     * */
    private var gallerySelectedPosition: Int? = null

    /**
     * Установить позицию отображаемого элемента при открытии диалогового окна галереи
     * */
    fun setGallerySelectedPosition(position: Int) {
        if (position < 0) {
            throw IllegalStateException("Game id wasn't passed")
        }
        gallerySelectedPosition = position
    }

    /**
     * Получить позицию элемента для отображения в диалоговом окне галереи
     * */
    fun getGallerySelectedPosition(): Int {
        return gallerySelectedPosition.takeIf {
            it != null
        } ?: throw IllegalStateException("Selected position hasn't been set")
    }
}