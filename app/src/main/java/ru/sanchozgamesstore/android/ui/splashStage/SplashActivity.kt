package ru.sanchozgamesstore.android.ui.splashStage

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.sanchozgamesstore.android.ui.authorizationStage.AuthorizationActivity
import ru.sanchozgamesstore.android.ui.mainStage.MainActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewModel.authorizationStatus.observe(this) { authorized ->
            val activity =
                MainActivity::class.java.takeIf { authorized } ?: AuthorizationActivity::class.java

            val intent = Intent(this, activity)
            startActivity(intent)
            finish()
        }
    }
}