package com.example.myapplication.ui.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.EventObserver
import com.example.myapplication.core.extension.startWithFinish
import com.example.myapplication.core.extension.viewModelProvider
import com.example.myapplication.ui.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.text.Typography.dagger

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val viewModel: SplashViewModel = viewModelProvider(viewModelFactory)
        viewModel.userSignStatus.observe(this, EventObserver { destination ->
            when (destination) {
                LaunchDestination.MAIN_ACTIVITY -> startWithFinish(MainActivity.callingIntent(this))
                LaunchDestination.LOGIN -> navigateToLogin()
            }
        })

        viewModel.shouldSignIn()
    }

    private fun navigateToLogin() {
        findNavController(R.id.splash_nav_host)
            .navigate(R.id.loginFragment)
    }

}