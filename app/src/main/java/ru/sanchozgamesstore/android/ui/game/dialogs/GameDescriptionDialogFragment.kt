package ru.sanchozgamesstore.android.ui.game.dialogs

import android.os.Bundle
import androidx.fragment.app.viewModels
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseDialogFragment
import ru.sanchozgamesstore.android.base.BaseDialogFragment.Companion.Configurator
import ru.sanchozgamesstore.android.data.domain.sealed.DialogWindowSize
import ru.sanchozgamesstore.databinding.DialogFragmentGameDescriptionBinding

class GameDescriptionDialogFragment : BaseDialogFragment<DialogFragmentGameDescriptionBinding>() {

    private val viewModel: GameDescriptionViewModel by viewModels()

    override fun Configurator.getConfigurator() {
        layout = R.layout.dialog_fragment_game_description
        height = DialogWindowSize.WrapContent
    }

    override fun parseArguments() {
        super.parseArguments()

        arguments?.let { args ->
            viewModel.description = args.getString(KEY_GAME_DESCRIPTION)
        }
    }

    override fun observeView() {
        super.observeView()

        binding.apply {
            btnDismiss.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun observeData() {
        super.observeData()

        binding.apply {
            tvDescription.text = viewModel.description
        }
    }

    companion object {
        private const val KEY_GAME_DESCRIPTION = "key.game_description"

        fun getBundle(description: String): Bundle {
            return Bundle().apply {
                putString(KEY_GAME_DESCRIPTION, description)
            }
        }

    }
}