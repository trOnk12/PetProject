package com.example.myapplication.feature.login.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extension.*
import com.example.myapplication.core.platform.BaseFragment
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.feature.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment
<LoginFragmentBinding, LoginViewModel>
    (R.layout.login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observe(passwordError, ::onPasswordError)
            observe(emailError, ::onEmailError)
            observe(event, ::onViewEvent)
            observe(snackBarMessage) { showSnackBar(signInButton, it) }
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
            signInButton.setOnClickListener { this@LoginFragment.viewModel.logIn() }
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
            is LoginViewEvent.OpenMainActivity -> {
                activity?.let {
                    startWithFinish(MainActivity.callingIntent(it))
                }
            }
        }
    }
}
