package ru.sanchozgamesstore.android.ui.mainStage.basket

import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.databinding.FragmentBasketBinding

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>() {
    override fun getLayoutID(): Int = R.layout.fragment_basket
}