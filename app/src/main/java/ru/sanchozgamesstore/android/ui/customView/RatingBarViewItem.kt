package ru.sanchozgamesstore.android.ui.customView

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseViewItem
import ru.sanchozgamesstore.android.data.domain.enums.RatingSpecies
import ru.sanchozgamesstore.android.data.domain.models.rating.RatingMap
import ru.sanchozgamesstore.android.ui.game.adapters.GameRatingAdapter
import ru.sanchozgamesstore.android.utils.removeItemDecorations
import ru.sanchozgamesstore.databinding.ViewItemRatingBarBinding

class RatingBarViewItem(
    binding: ViewItemRatingBarBinding,
) : BaseViewItem<ViewItemRatingBarBinding>(binding) {

    /**
     * Адаптер для перечисления рейтингов под полоской рейтинга
     * */
    private var gameRatingAdapter: GameRatingAdapter? = null

    init {
        binding.llRatingBar.setBackgroundColor(
            ResourcesCompat.getColor(
                binding.root.resources,
                RatingSpecies.UNDEFINED.color,
                null
            )
        )

        gameRatingAdapter = GameRatingAdapter()

        binding.rvRatings.apply {
            layoutManager = FlexboxLayoutManager(root.context).apply {
                //Если айтем не помещается в строку, то переносится на следующую
                flexDirection = FlexDirection.ROW

                //Устанавливаются одинаковые отступы между айтемами строки
                justifyContent = JustifyContent.FLEX_START
            }

            //Удалить все декораторы, если они были
            removeItemDecorations()

            //Горизонтальный разделитель
            addItemDecoration(FlexboxItemDecoration(root.context).apply {
                //Уставливаем направление разделителя - между строками
                setOrientation(
                    FlexboxItemDecoration.HORIZONTAL
                )

                //Устанавливаем сам разделитель
                setDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.divider_horizontal_10,
                        null
                    )
                )

            })

            adapter = gameRatingAdapter
        }
    }

    /**
     * Очистить полоску рейтинга
     * */
    private fun clearRatingBar() {
        binding.llRatingBar.removeAllViews()
    }

    /**
     * Установить рейтинги во view
     *
     * @param ratingMap мапа "разновидность рейтинга" - "данные рейтинга"
     * */
    fun setRatings(ratingMap: RatingMap) {
        clearRatingBar()

        //Игра была оценена пользователями
        val isRated = ratingMap.entries.sumOf {
            it.value.count
        } != 0

        //Если оценки есть, то отображаем полоску рейтингов, иначе представление отсутствия данных
        binding.groupContent.isVisible = isRated
        binding.lEmpty.root.isVisible = !isRated

        if (!isRated) {
            return
        }

        /*
        * Т.к. мапа в виде ключей имеет все известные виды рейтинга, то включаем в список UNDEFINED
        * рейтинг только в случае, если количество его оценок отличается от нуля
        * */
        /**
         *  Все рейтинги, включая UNDEFINED, если кол-во его оценок отличается от нуля
         * */
        val availableRatings = ratingMap.entries.filter {
            if (it.key == RatingSpecies.UNDEFINED) {
                it.value.count != 0
            } else {
                true
            }
        }

        gameRatingAdapter?.addAll(availableRatings)

        /*
        * Проходимся по всем доступным рейтингам и уставливаем его процентное соотношение в виде
        * веса лейаута
        * */
        availableRatings.map { (key, value) ->
            val view = View(root.context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                ).apply {
                    //Вес лейаута == Процентное соотношение рейтинга
                    weight = value.percent.toFloat()
                }

                setBackgroundColor(ResourcesCompat.getColor(resources, key.color, null))
            }

            //Добавляем новую вью в полоску рейтинга
            binding.llRatingBar.addView(view)
        }
    }

    override fun clear() {
        super.clear()
        gameRatingAdapter = null
    }
}