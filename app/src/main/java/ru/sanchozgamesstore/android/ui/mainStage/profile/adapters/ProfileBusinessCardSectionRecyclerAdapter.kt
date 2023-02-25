package ru.sanchozgamesstore.android.ui.mainStage.profile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.sanchozgamesstore.android.base.BaseRecyclerAdapter
import ru.sanchozgamesstore.android.base.BaseViewHolder
import ru.sanchozgamesstore.android.data.domain.models.user.businessCard.BusinessCardModel
import ru.sanchozgamesstore.databinding.ItemProfileBusinessCardSectionBinding

class ProfileBusinessCardSectionAdapter : BaseRecyclerAdapter<BusinessCardModel>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BusinessCardModel> {
        return ProfileBusinessCardSectionViewHolder(
            ItemProfileBusinessCardSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BusinessCardModel>, position: Int) {
        holder.bind(itemsList[position])
    }

    class ProfileBusinessCardSectionViewHolder(private val binding: ItemProfileBusinessCardSectionBinding) :
        BaseViewHolder<BusinessCardModel>(binding.root) {
        override fun bind(data: BusinessCardModel) {
            binding.apply {
                tvLabel.text = root.context.getString(data.section.title)
                tvTitle.text = data.content
            }
        }
    }

}