package ru.sanchozgamesstore.android.ui.mainStage.profile

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.data.domain.models.game.GameDetailsModel
import ru.sanchozgamesstore.android.data.domain.models.user.UserModel
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.android.data.domain.response.Resource.Status
import ru.sanchozgamesstore.android.ui.adapters.GameListAdapter
import ru.sanchozgamesstore.android.ui.adapters.GameListShimmerAdapter
import ru.sanchozgamesstore.android.ui.mainStage.profile.adapters.ProfileBusinessCardSectionAdapter
import ru.sanchozgamesstore.android.utils.itemDecoration.GridItemDecoration
import ru.sanchozgamesstore.android.utils.itemDecoration.OrientationItemDecoration
import ru.sanchozgamesstore.android.utils.pictureLoadParams
import ru.sanchozgamesstore.android.utils.shimmerEnabled
import ru.sanchozgamesstore.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels()

    /**
     * Адаптер списка избранных игр пользователя
     * */
    private var gameListAdapter: GameListAdapter? = null

    /**
     * Адаптер шиммера для списка избранных игр
     * */
    private var gameListShimmerAdapter: GameListShimmerAdapter? = null

    /**
     * Адаптер разделов визитной карточки пользователя
     * */
    private var businessCardSectionAdapter: ProfileBusinessCardSectionAdapter? = null

    override fun getLayoutID(): Int = R.layout.fragment_profile

    override fun setUpViews() {
        super.setUpViews()

        businessCardSectionAdapter = ProfileBusinessCardSectionAdapter()

        gameListAdapter = GameListAdapter()

        gameListShimmerAdapter = GameListShimmerAdapter().apply {
            submitData(List(6) {})
        }

        binding.apply {
            blockBusinessCard.rvSections.apply {
                adapter = businessCardSectionAdapter

                addItemDecoration(
                    OrientationItemDecoration(
                        insetBetween = 4,
                    )
                )
            }

            blockContent.lShimmer.rvShimmer.apply {
                adapter = gameListShimmerAdapter
                layoutManager = GridLayoutManager(context, GAMES_SPAN_COUNT)
                addItemDecoration(GridItemDecoration(8, 8))
            }

            blockContent.rvGames.apply {
                adapter = gameListAdapter
                layoutManager = GridLayoutManager(context, GAMES_SPAN_COUNT)
                addItemDecoration(GridItemDecoration(8, 8))
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

        viewModel.favoriteGames.observe(viewLifecycleOwner) {
            fillFavoriteGames(it)
        }

        viewModel.accountToken.observe(viewLifecycleOwner) {
            Log.e("Account Token", "$it")
        }
    }

    /**
     * Заполнение визитной карточке на странице пользователя
     * */
    private fun fillBusinessCard(profile: Resource<UserModel>) {
        binding.apply {
            blockBusinessCard.lShimmer.sflRoot.shimmerEnabled(profile.status == Status.LOADING)

            blockBusinessCard.cvBusinessCard.isVisible = profile.status != Status.LOADING

            blockBusinessCard.ivAvatar.apply image@{
                load(profile.data?.avatar) {
                    pictureLoadParams(this@image)
                }
            }

            if (profile.dataLoaded) {
                businessCardSectionAdapter?.submitData(profile.data!!.businessCardSections)
            }
        }
    }

    /**
     * Заполнение блока избранных игр пользователя
     * */
    private fun fillFavoriteGames(favoriteGames: Resource<List<GameDetailsModel>>) {
        binding.apply {
            blockContent.lShimmer.sflRoot.shimmerEnabled(favoriteGames.status == Status.LOADING)

            blockContent.rvGames.isVisible = favoriteGames.dataLoaded

            blockContent.lEmpty.root.isVisible = favoriteGames.dataNotLoaded
                    || favoriteGames.dataLoaded && favoriteGames.data!!.isEmpty()

            if (favoriteGames.dataLoaded) {
                gameListAdapter?.submitList(favoriteGames.data!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        businessCardSectionAdapter = null
        gameListAdapter = null
        gameListShimmerAdapter = null
    }

    companion object {
        private const val GAMES_SPAN_COUNT = 2
    }
}