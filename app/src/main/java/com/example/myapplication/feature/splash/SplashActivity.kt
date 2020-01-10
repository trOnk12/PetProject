package com.example.myapplication.feature.splash

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.startWithFinish
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.core.commons.base.BaseActivity
import com.example.myapplication.feature.MainActivity

class SplashActivity : BaseActivity() {

    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        initializeViewModel()
        initializeObserver()
    }

    private fun initializeViewModel() {
        viewModel = viewModel(provider)
    }

    private fun initializeObserver() {
        observe(viewModel.event, ::onEventChange)
    }

    private fun onEventChange(event: SplashViewEvent) {
        when (event) {
            SplashViewEvent.OpenMainActivity -> startWithFinish(MainActivity.callingIntent(this))
            SplashViewEvent.OpenLoginFragment -> findNavController(R.id.splash_nav_host)
                .navigate(R.id.loginFragment)
        }
    }
}
