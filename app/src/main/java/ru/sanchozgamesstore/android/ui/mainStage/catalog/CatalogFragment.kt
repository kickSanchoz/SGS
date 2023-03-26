package ru.sanchozgamesstore.android.ui.mainStage.catalog

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.utils.DeepLinkController
import ru.sanchozgamesstore.databinding.FragmentCatalogBinding

@AndroidEntryPoint
class CatalogFragment : BaseFragment<FragmentCatalogBinding>() {

    private val viewModel: CatalogViewModel by viewModels()

    override fun getLayoutID(): Int = R.layout.fragment_catalog

    override fun observeView() {
        super.observeView()

        binding.btnSetToken.setOnClickListener {
            viewModel.setAccountToken("censoredToken")
        }

        binding.btnDeleteToken.setOnClickListener {
            viewModel.deleteAccountToken()
        }

        binding.btnToGamePage.setOnClickListener {
            DeepLinkController(this).openGamePageFragment(1)
        }
    }
}