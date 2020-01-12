package com.example.myapplication.feature.register.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PetProject
import com.example.myapplication.R
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.showSnackBar
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.RegisterFragmentBinding
import com.example.myapplication.domain.authentication.AuthenticationSource
import com.example.myapplication.feature.register.ui.di.DaggerRegisterComponent
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>
    (R.layout.register_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observe(event, ::onViewEvent)
        }
    }

    override fun onInitDependency() {
        DaggerRegisterComponent
            .builder()
            .coreComponent(PetProject.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun onInitViewModel() {
        viewModel = viewModel(provider)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.signInButton.setOnClickListener { viewModel.register(AuthenticationSource.FireBase) }
        viewBinding.registerInfo.setOnClickListener { findNavController().navigate(R.id.loginFragment) }
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
