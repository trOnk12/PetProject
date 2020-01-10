package com.example.myapplication.feature.register.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.showSnackBar
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.core.extensions.startWithFinish
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.RegisterFragmentBinding
import com.example.myapplication.domain.authentication.AuthenticationSource
import com.example.myapplication.feature.MainActivity
import com.example.myapplication.feature.login.ui.LoginViewEvent
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.register_fragment.*
import kotlinx.android.synthetic.main.register_fragment.emailAddress
import kotlinx.android.synthetic.main.register_fragment.password
import kotlinx.android.synthetic.main.register_fragment.signInButton

class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>
    (R.layout.register_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observe(event, ::onViewEvent)
        }
    }

    override fun onInitDataBinding() {
        viewModel = viewModel(provider)

        viewBinding.apply {
            viewModel = this@RegisterFragment.viewModel
            signInButton.setOnClickListener {
                this@RegisterFragment.viewModel.register(
                    AuthenticationSource.FireBase
                )
            }
            registerInfo.setOnClickListener { findNavController().navigate(R.id.loginFragment) }
        }
    }

    override fun onInitDependency() {

    }

    override fun onInitViewModel() {

    }

    private fun onViewEvent(event: RegisterViewEvent) {
        when (event) {
            is RegisterViewEvent.ShowPasswordError -> onPasswordError(event.error)
            is RegisterViewEvent.ShowRepeatPasswordError -> onRepeatPasswordError(event.error)
            is RegisterViewEvent.ShowEmailError -> onEmailError(event.error)
            is RegisterViewEvent.ShowSnackBarMessage -> showSnackBar(signInButton, event.message)
        }
    }

    private fun onEmailError(validationError: ValidationError) {
        when (validationError) {
            is ValidationError.IsEmpty -> emailAddress.error = "Empty password"
        }
    }

    private fun onPasswordError(validationError: ValidationError) {
        when (validationError) {
            is ValidationError.IsEmpty -> password.error = " Empty password"
        }
    }

    private fun onRepeatPasswordError(validationError: ValidationError) {
        when (validationError) {
            is ValidationError.IsEmpty -> repeatPassword.error = "Empty password"
        }
    }
}
