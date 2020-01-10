package com.example.myapplication.feature.login.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extensions.*
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.domain.authentication.AuthenticationSource
import com.example.myapplication.feature.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>
    (R.layout.login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observe(event, ::onViewEvent)
        }
    }

    override fun onInitDependency() {

    }

    override fun onInitViewModel() {
        viewModel = viewModel(provider)
    }

    override fun onInitDataBinding() {
        viewBinding.apply {
            viewModel = viewModel
            signInButton.setOnClickListener {
                this@LoginFragment.viewModel.logIn(
                    AuthenticationSource.FireBase
                )
            }
            registerInfo.setOnClickListener { findNavController().navigate(R.id.registerFragment) }
        }
    }

    private fun onEmailError(validationError: ValidationError) {
        when (validationError) {
            is ValidationError.IsEmpty -> emailAddress.error = "Empty password"
        }
    }

    private fun onPasswordError(validationError: ValidationError?) {
        when (validationError) {
            is ValidationError.IsEmpty -> password.error = " Empty password"
        }
    }

    private fun onViewEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.ShowPasswordError -> onPasswordError(event.error)
            is LoginViewEvent.ShowEmailError -> onEmailError(event.error)
            is LoginViewEvent.ShowSnackBarMessage -> showSnackBar(signInButton, event.message)
            is LoginViewEvent.OpenMainActivity -> {
                activity?.let {
                    startWithFinish(MainActivity.callingIntent(it))
                }
            }
        }
    }
}
