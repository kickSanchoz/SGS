package ru.sanchozgamesstore.android.ui.mainStage

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.R
import ru.sanchozgamesstore.android.base.BaseActivity
import ru.sanchozgamesstore.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun getFragmentContainerId(): Int = R.id.fcv_activity_main

    override fun setUpView() {
        super.setUpView()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvSections.setupWithNavController(navController)
    }
}