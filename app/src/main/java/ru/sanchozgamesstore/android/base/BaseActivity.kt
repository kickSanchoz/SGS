package ru.sanchozgamesstore.android.base

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import ru.sanchozgamesstore.android.utils.hideKeyboard

abstract class BaseActivity<VB : ViewDataBinding>(
    val bindingFactory: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private var mViewBinding: VB? = null
    val binding get() = mViewBinding!!

    /** Контейнер фрагментов для ручного управления фрагментами в каждом отдельном активити */
    abstract fun getFragmentContainerId(): Int

    /**
     * Нужен для переходов, в этот момент binding == null!!!!
     * */
    @CallSuper
    open fun configureActivity() {
    }

    @CallSuper
    open fun setUpView() {
    }

    @CallSuper
    open fun observeView() {
    }

    @CallSuper
    open fun observeData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureActivity()
        mViewBinding = bindingFactory(layoutInflater)
        setUpView()
        observeView()
        observeData()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewBinding = null
    }

    //Свернуть клавиатуру по нажатии на пустое место экрана
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) { //Нажатие пальцем по экрану
            val v = currentFocus //Получить текущий фокус
            if (v is EditText) {   //Установлен ли фокус в поле EditText
                val outRect = Rect() //Создание экзампляра класса прямоугольника
                v.getGlobalVisibleRect(outRect)
                val inputX = event.rawX.toInt()
                val inputY = event.rawY.toInt()
                if (!outRect.contains(inputX, inputY)
                ) {    //Если коориднаты места нажатия не находятся в координатах прямоугольников EditText, то
                    v.clearFocus() //Очисть фокус с поля EditText
                    hideKeyboard(v) //Т.к. фокус потерян, то убрать клавиатуру
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}