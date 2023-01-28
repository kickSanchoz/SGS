package ru.sanchozgamesstore.android.ui.customView

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import ru.sanchozgamesstore.android.base.BaseViewItem
import ru.sanchozgamesstore.databinding.AppBarDefaultBinding

class AppBarDefaultViewItem(
    binding: AppBarDefaultBinding,
) : BaseViewItem<AppBarDefaultBinding>(binding) {

    /**
     * Действие на кнопку "назад"
     * */
    private var buttonBackListener: (() -> Unit)? = null

    /**
     * Действие на доп. кнопку в правой части аппбара
     * */
    private var buttonActionListener: (() -> Unit)? = null

    init {
        binding.apply {
            btnAction.setOnClickListener {
                buttonActionListener?.invoke()
            }

            btnBack.setOnClickListener {
                buttonBackListener?.invoke()
            }
        }
    }

    /**
     * Установить изображение доп. кнопки аппбара
     * */
    fun setButtonActionDrawable(@DrawableRes drawable: Int?) {
        binding.apply {
            btnAction.apply {
                visibility = View.VISIBLE.takeIf { drawable != null } ?: View.INVISIBLE

                if (drawable != null) {
                    icon = ResourcesCompat.getDrawable(root.resources, drawable, null)
                }
            }
        }
    }

    /**
     * Установить текст аппбара
     * */
    fun setTitle(title: String?) {
        binding.apply {
            tvBarTitle.apply {
                isVisible = !title.isNullOrBlank()

//                title?.let {
//                }
                text = title
            }
        }
    }

    /**
     * Установить действие на кнопку "назад"
     * */
    fun setButtonBackListener(block: () -> Unit) {
        buttonBackListener = block
    }

    /**
     * Установить действие на доп. кнопку в правой части аппбара
     * */
    fun setButtonActionListener(block: () -> Unit) {
        buttonActionListener = block
    }

    override fun clear() {
        super.clear()

        buttonBackListener = null
        buttonActionListener = null
    }
}