package ru.sanchozgamesstore.android.ui.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameParentPlatformBinding

class GameParentPlatformAdapter :
    RecyclerView.Adapter<GameParentPlatformAdapter.ParentPlatformViewHolder>() {

    private val iconList = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentPlatformViewHolder {
        val view = ItemGameParentPlatformBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ParentPlatformViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentPlatformViewHolder, position: Int) {
        holder.bind(iconList[position])
    }

    override fun getItemCount(): Int = iconList.size

    /**
     * Добавление родительских платформ в адаптер
     * */
    fun addAll(parentPlatformIcons: List<Int>) {
        iconList.clear()
        iconList.addAll(parentPlatformIcons)
        notifyDataSetChanged()
    }

    class ParentPlatformViewHolder(private val binding: ItemGameParentPlatformBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(icon: Int) {
            val iconRes = ResourcesCompat.getDrawable(binding.root.resources, icon, null)

            binding.ivIcon.load(iconRes) {
                defaultPictureLoadParams(binding.root.context)
            }
        }
    }
}