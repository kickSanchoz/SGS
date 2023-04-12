package ru.sanchozgamesstore.android.ui.authorizationStage.login

import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseFragment
import ru.sanchozgamesstore.android.data.domain.response.Resource
import ru.sanchozgamesstore.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getLayoutID(): Int = R.layout.fragment_login

    override fun setUpViews() {
        super.setUpViews()

        binding.vm = viewModel
    }

    override fun observeView() {
        super.observeView()

        binding.apply {
            btnContinue.setOnClickListener {
                viewModel.login()
            }
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.loginState.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.e("LoginState", "toMainActivity")
                }
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {

                }
                Resource.Status.NETWORK_ERROR -> {

                }
            }
        }
    }
}