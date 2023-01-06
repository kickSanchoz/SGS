package ru.sanchozgamesstore.android.ui.mainStage.catalog

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.databinding.FragmentCatalogBinding

@AndroidEntryPoint
class CatalogFragment : BaseFragment<FragmentCatalogBinding>() {
    override fun getLayoutID(): Int = R.layout.fragment_catalog

    override fun observeView() {
        super.observeView()

        binding.btnToGamePage.setOnClickListener {
            val action = CatalogFragmentDirections.actionCatalogFragmentToGamePageFragment(1)
            findNavController().navigate(action)
        }
    }
}