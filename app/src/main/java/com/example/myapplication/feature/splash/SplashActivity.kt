package com.example.myapplication.feature.splash

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extension.observe
import com.example.myapplication.core.extension.startWithFinish
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.core.platform.BaseActivity
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
