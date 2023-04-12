package ru.sanchozgamesstore.android.ui.authorizationStage

import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseActivity
import ru.sanchozgamesstore.databinding.ActivityAuthorizationBinding

@AndroidEntryPoint
class AuthorizationActivity :
    BaseActivity<ActivityAuthorizationBinding>(ActivityAuthorizationBinding::inflate) {

    override fun getFragmentContainerId(): Int = R.id.nav_graph_authorization_activity
}