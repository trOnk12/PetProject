package com.example.myapplication.feature.splash

import com.example.myapplication.core.livedata.SingleLiveData
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val event by lazy {
        val liveData = SingleLiveData<SplashViewEvent>()
        liveData.value = shouldSignIn()

        return@lazy liveData
    }

    private fun shouldSignIn(): SplashViewEvent {
        return if (userRepository.isUserSignIn())
            SplashViewEvent.OpenMainActivity
        else
            SplashViewEvent.OpenLoginFragment
    }
}
