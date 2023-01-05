package ru.sanchozgamesstore.android.ui.mainStage.catalog

import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.databinding.FragmentCatalogBinding

@AndroidEntryPoint
class CatalogFragment : BaseFragment<FragmentCatalogBinding>() {
    override fun getLayoutID(): Int = R.layout.fragment_catalog
}