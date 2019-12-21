package com.example.myapplication.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userSignInStatus = MutableLiveData<Event<LaunchDestination>>()
    val userSignStatus: LiveData<Event<LaunchDestination>>
        get() = _userSignInStatus

    fun shouldSignIn() {
        if (userRepository.isSignIn())
            _userSignInStatus.value = Event(LaunchDestination.MAIN_ACTIVITY)
        else
            _userSignInStatus.value = Event(LaunchDestination.LOGIN)
    }

}

enum class LaunchDestination {
    LOGIN,
    MAIN_ACTIVITY
}