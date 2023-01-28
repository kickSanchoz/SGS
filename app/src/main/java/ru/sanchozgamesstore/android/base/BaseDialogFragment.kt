package ru.sanchozgamesstore.android.base

import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.data.domain.sealed.DialogWindowSize
import ru.sanchozgamesstore.android.ui.customView.AppBarDefaultViewItem
import ru.sanchozgamesstore.databinding.AppBarDefaultBinding

abstract class BaseDialogFragment<VB : ViewDataBinding> : DialogFragment() {
    private var _binding: VB? = null
    val binding
        get() = _binding!!

    private val configurator = Configurator().apply {
        getConfigurator()

        if (layout == null) {
            throw IllegalStateException("Configurator: layout value is required")
        }
    }

    /** Получение настроек конфигуратора */
    abstract fun Configurator.getConfigurator()

    /** Кастомное представление аппбара */
    private var appBarDefaultViewItem: AppBarDefaultViewItem? = null

    override fun getTheme(): Int = configurator.theme

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, configurator.layout!!, container, false)
        setupView()
        observeView()
        observeData()
        return binding.root
    }

    /**
     * Установка и настройка представлений
     * */
    @CallSuper
    open fun setupView() {
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
    override fun onStart() {
        super.onStart()
        configureDialogFragment() //Настройка диалога должна происходить в onStart, иначе параметры лейаута не применяются
    }

    private fun configureDialogFragment() {
        binding.lifecycleOwner = viewLifecycleOwner

        dialog?.apply {
            isCancelable = configurator.isCancelable
            setCanceledOnTouchOutside(configurator.isCloseableOnTouchOutside)

            window?.apply {
                /*
                * Флаг позволяет всегда отрисовывать фон статус бара и затемняет его, если
                * windowIsFloating == true.
                * Например, при высоте окна MATCH_PARENT статус бар становится полностью чёрным, а с
                * данным флагом затемняется и остаётся полупрозрачным
                * */
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                //Делает статус бар прозрачным и он становится colorPrimary цвета приложения
                statusBarColor = ResourcesCompat.getColor(resources, R.color.transparent, null)

                configurator.animationStyle?.apply {
                    setWindowAnimations(this)
                }

                setLayout(
                    configurator.width.value,
                    configurator.height.value
                )
                setGravity(configurator.gravity)
            } ?: run {
                throw IllegalStateException("Activity window is not visual")
            }
        } ?: run {
            throw IllegalStateException("Dialog is not found")
        }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                dismiss()
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

    /**
     * Установить текст аппбара на фрагменте
     * */
    protected fun setDefaultAppBarTitle(
        title: String?
    ) {
        appBarDefaultViewItem?.apply {
            //Установка заголовка
            setTitle(title)
        } ?: throw IllegalStateException("Layout for app bar hasn't been set")
    }

    companion object {
        /**
         * Класс настройки диалог фрагмента
         * */
        class Configurator {

            /**
             * Лейаут для отображения
             *
             * По умолчанию: null
             * */
            var layout: Int? = null

            /**
             * Гравити окна
             *
             * По умолчанию: Gravity.BOTTOM
             * */
            var gravity: Int = Gravity.BOTTOM

            /**
             * Тема диалог фрагмента
             *
             * По умолчанию: R.style.DialogFragmentTheme
             * */
            @StyleRes
            var theme: Int = R.style.DialogFragmentTheme

            /**
             * Ширина окна
             *
             * По умолчанию: DialogWindowSize.MatchParent
             * */
            var width: DialogWindowSize = DialogWindowSize.MatchParent

            /**
             * Высота окна
             *
             * По умолчанию: DialogWindowSize.WrapContent
             * */
            var height: DialogWindowSize = DialogWindowSize.WrapContent

            /**
             * Возможно ли закрыть окно нажатием кнопки назад
             *
             * (Для выключения необходимо также установить isCloseableOnTouchOutside = false)
             *
             * По умолчанию: true
             *
             * @see isCloseableOnTouchOutside
             * */
            var isCancelable: Boolean = true

            /**
             * Возможно ли закрыть касанием вне окна
             *
             * По умолчанию: true
             * */
            var isCloseableOnTouchOutside: Boolean = true

            /**
             * Стиль анимации
             *
             * По умолчанию: R.style.DialogAnimation
             * */
            @StyleRes
            var animationStyle: Int? = R.style.DialogAnimation
        }
    }
}