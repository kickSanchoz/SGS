package ru.sanchozgamesstore.android.ui.mainStage.profile

import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status
import ru.sanchozgamesstore.android.ui.mainStage.profile.adapters.ProfileBusinessCardSectionAdapter
import ru.sanchozgamesstore.android.utils.defaultPictureLoadParams
import ru.sanchozgamesstore.android.utils.itemDecoration.OrientationItemDecoration
import ru.sanchozgamesstore.android.utils.shimmerEnabled
import ru.sanchozgamesstore.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels()

    //TODO comments
    private var businessCardSectionAdapter: ProfileBusinessCardSectionAdapter? = null

    override fun getLayoutID(): Int = R.layout.fragment_profile

    override fun setUpViews() {
        super.setUpViews()

        businessCardSectionAdapter = ProfileBusinessCardSectionAdapter()

        binding.apply {
            blockBusinessCard.rvSections.apply {
                adapter = businessCardSectionAdapter

                addItemDecoration(
                    OrientationItemDecoration(
                        insetBetween = 4,
                    )
                )
            }
        }
    }

    override fun observeView() {
        super.observeView()

        binding.apply {
            srlPage.setOnRefreshListener {
                viewModel.refreshPage()
            }
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.profile.observe(viewLifecycleOwner) {
            binding.srlPage.isRefreshing = false

            fillBusinessCard(it)
        }
    }

    private fun fillBusinessCard(profile: Resource<UserModel>) {
        binding.apply {
            blockBusinessCard.lShimmer.sflRoot.shimmerEnabled(profile.status == Status.LOADING)

            blockBusinessCard.cvBusinessCard.isVisible = profile.status != Status.LOADING

            blockBusinessCard.ivAvatar.load(profile.data?.avatar) {
                defaultPictureLoadParams(root.context)
                listener(
                    onError = { request, result ->
                        result.drawable?.setTint(
                            ResourcesCompat.getColor(
                                binding.root.resources,
                                R.color.white,
                                null
                            )
                        )
                    }
                )
            }

            if (profile.dataLoaded) {
                businessCardSectionAdapter?.submitData(profile.data!!.businessCardSections)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        businessCardSectionAdapter = null
    }
}