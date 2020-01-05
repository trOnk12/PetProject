package com.example.myapplication.feature.register.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extension.observe
import com.example.myapplication.core.extension.showSnackBar
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.core.platform.BaseFragment
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.RegisterFragmentBinding
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : BaseFragment
<RegisterFragmentBinding, RegisterViewModel>
    (R.layout.register_fragment) {

    override fun onInitDataBinding() {
        viewModel = viewModel(provider)

        viewBinding.apply {
            viewModel = this@RegisterFragment.viewModel
            signInButton.setOnClickListener { this@RegisterFragment.viewModel.register() }
            registerInfo.setOnClickListener { findNavController().navigate(R.id.loginFragment) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            observe(passwordError, ::onPasswordError)
            observe(repeatPasswordError, ::onRepeatPasswordError)
            observe(emailError, ::onEmailError)
            observe(snackBarMessage) { showSnackBar(signInButton, it) }
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
