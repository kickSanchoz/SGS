package ru.sanchozgamesstore.android.ui.mainStage.catalog.game

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.databinding.FragmentGamePageBinding

@AndroidEntryPoint
class GamePageFragment : BaseFragment<FragmentGamePageBinding>() {

    private val viewModel: GamePageViewModel by viewModels()

    override fun getLayoutID(): Int = R.layout.fragment_game_page

    override fun parseArguments() {
        super.parseArguments()

        val args: GamePageFragmentArgs by navArgs()
        viewModel.gameId.value = args.gameId
    }

    override fun observeData() {
        super.observeData()

        viewModel.gameId.observe(viewLifecycleOwner) {
            viewModel.getGameStores(it)
        }

        viewModel.stores.observe(viewLifecycleOwner) {
            Log.e(TAG, "$it")
        }
    }

    companion object {
        private const val TAG = "GamePageFragment"
    }
}