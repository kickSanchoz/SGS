package ru.sanchozgamesstore.android.ui.mainStage.catalog.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.data.domain.models.game.screnshot.ScreenshotModel
import ru.sanchozgamesstore.android.utils.getPlaceholder
import ru.sanchozgamesstore.databinding.ItemGameScreenshotBinding

class GameScreenshotAdapter :
    RecyclerView.Adapter<GameScreenshotAdapter.GameScreenshotViewHolder>() {

    private val screenshotList = mutableListOf<ScreenshotModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameScreenshotViewHolder {
        val view = ItemGameScreenshotBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameScreenshotViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameScreenshotViewHolder, position: Int) {
        holder.bind(screenshotList[position])
    }

    override fun getItemCount(): Int = screenshotList.size

    /**
     * Добавление скриншотов в адаптер
     * */
    fun addAll(screenshots: List<ScreenshotModel>) {
        screenshotList.clear()
        screenshotList.addAll(screenshots)
        notifyDataSetChanged()
    }

    class GameScreenshotViewHolder(private val binding: ItemGameScreenshotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(screenshot: ScreenshotModel) {
            binding.ivScreenshot.load(screenshot.image) {
                crossfade(true)
                placeholder(getPlaceholder(binding.root.context))
                error(R.drawable.ic_question_mark)
            }
        }
    }
}