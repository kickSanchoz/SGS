package ru.sanchozgamesstore.android.ui.game.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.sanchozgamesstore.android.data.domain.models.game.GameToStoreModel
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.databinding.ItemGameStoreBinding

class GameStoreAdapter : RecyclerView.Adapter<GameStoreAdapter.GameStoreViewHolder>() {

    private val storeList = mutableListOf<GameToStoreModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameStoreViewHolder {
        return GameStoreViewHolder(
            ItemGameStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameStoreViewHolder, position: Int) {
        holder.bind(storeList[position])
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    /**
     * Добавить все интернет-магазины в адаптер
     *
     * @param stores список интернет-магазинов
     * */
    fun addAll(stores: List<GameToStoreModel>) {
        storeList.clear()
        storeList.addAll(stores)
        notifyItemRangeChanged(0, storeList.size)
    }

    class GameStoreViewHolder(private val binding: ItemGameStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: GameToStoreModel) {
            binding.apply {
                tvTitle.text = store.name

                ivIcon.apply image@{
                    load(store.icon) {
                        pictureLoadParams(this@image)
                    }
                }

                cvGameStore.setOnClickListener {
                    try {
                        val uri = Uri.parse(store.url)

                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        root.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Log.e("Uri parse error", "${e.message}")
                    }
                }
            }
        }
    }
}