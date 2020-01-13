package com.example.myapplication.feature.login.ui

import androidx.navigation.fragment.findNavController
import com.example.myapplication.PetProject
import com.example.myapplication.R
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.showSnackBar
import com.example.myapplication.core.extensions.startWithFinish
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.di.modules.authentication.AuthenticationSources
import com.example.myapplication.feature.MainActivity
import com.example.myapplication.feature.login.ui.di.DaggerLoginComponent
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>
    (R.layout.login_fragment) {

    override fun onInitDependency() {
        DaggerLoginComponent
            .builder()
            .coreComponent(PetProject.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun onInitViewModel() {
        viewModel = viewModel(provider)
        viewModel.apply {
            observe(event, ::onViewEvent)
        }
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.signInButton.setOnClickListener { viewModel.logIn(AuthenticationSources.FIREBASE) }
        viewBinding.registerInfo.setOnClickListener { findNavController().navigate(R.id.registerFragment) }
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
}
