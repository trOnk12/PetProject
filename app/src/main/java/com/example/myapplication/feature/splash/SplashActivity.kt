package com.example.myapplication.feature.splash

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.myapplication.PetProject
import com.example.myapplication.R
import com.example.myapplication.core.commons.base.BaseActivity
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.startWithFinish
import com.example.myapplication.feature.MainActivity
import com.example.myapplication.feature.splash.di.DaggerSplashComponent
import javax.inject.Inject

class SplashActivity :
    BaseActivity(R.layout.splash_activity) {

    @Inject
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.event, ::onEventChange)
    }

    override fun initDependencyComponent() {
        DaggerSplashComponent
            .builder()
            .coreComponent(PetProject.coreComponent(this))
            .build()
            .inject(this)
    }

    private fun onEventChange(event: SplashViewEvent) {
        when (event) {
            SplashViewEvent.OpenMainActivity -> startWithFinish(MainActivity.callingIntent(this))
            SplashViewEvent.OpenLoginFragment -> findNavController(R.id.splash_nav_host)
                .navigate(R.id.loginFragment)
        }
    }
}
