package ru.sanchozgamesstore.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ru.sanchozgamesstore.android.ui.customView.AppBarDefaultViewItem
import ru.sanchozgamesstore.databinding.AppBarDefaultBinding

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    private var mViewBinding: VB? = null
    val binding get() = mViewBinding!!

    /** Кастомное представление аппбара */
    private var appBarDefaultViewItem: AppBarDefaultViewItem? = null

    abstract fun getLayoutID(): Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    /**
     * Получение аргументов при открытии фрагмента
     * */
    @CallSuper
    open fun parseArguments() {
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
        setUpViews()
        observeView()
        observeData()
        return binding.root
    }

    /**
     * Установка и настройка представлений
     * */
    @CallSuper
    open fun setUpViews() {
    }

    /**
     * Наблюдение за изменением представлений
     * */
    @CallSuper
    open fun observeView() {
    }

    /**
     * Наблюдение за изменением данных
     * */
    @CallSuper
    open fun observeData() {
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        mViewBinding = null
        appBarDefaultViewItem = null
    }

    /**
     * Настроить аппбар на фрагменте
     * */
    protected fun setupDefaultAppBar(
        layout: AppBarDefaultBinding,
        title: String? = null,
        @DrawableRes btnActionDrawable: Int? = null,
        onClickButtonAction: (() -> Unit)? = null,
    ) {
        appBarDefaultViewItem = AppBarDefaultViewItem(layout).apply {
            //Действие на кнопку "назад"
            setButtonBackListener {
                activity?.onBackPressed()
            }

            //Установка заголовка
            setTitle(title)

            //Изображение на доп. кнопку справа
            setButtonActionDrawable(btnActionDrawable)

            //Действие на доп. кнопку справа
            onClickButtonAction?.let {
                setButtonActionListener(it)
            }
        }
    }
}