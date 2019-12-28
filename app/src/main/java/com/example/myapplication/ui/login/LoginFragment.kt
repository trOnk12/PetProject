package com.example.myapplication.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.Event
import com.example.myapplication.core.EventObserver
import com.example.myapplication.core.extension.startWithFinish
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.login_fragment.*

import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val loginViewModel: LoginViewModel = viewModel(provider)
        loginViewModel.apply {
            passwordError.observe(this@LoginFragment, Observer(::handlePasswordError))
            emailError.observe(this@LoginFragment, Observer(::handleEmailError))
            navigateToMainActivity.observe(this@LoginFragment, EventObserver(::openMainActivity))
            snackBarMessage.observe(this@LoginFragment, EventObserver(::showSnackBar))
        }

        binding.apply {
            viewModel = loginViewModel
            lifecycleOwner = this@LoginFragment
            signInButton.setOnClickListener { loginViewModel.logIn() }
            registerInfo.setOnClickListener { openRegisterFragment() }
        }

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun handleEmailError(validationError: ValidationError) {
        when (validationError) {
            is ValidationError.IsEmpty -> emailAddress.error = "Empty password"
        }
    }

    private fun handlePasswordError(validationError: ValidationError?) {
        when (validationError) {
            is ValidationError.IsEmpty -> password.error = " Empty password"
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(signInButton, message, Snackbar.LENGTH_LONG).show()
    }

    private fun openMainActivity(user: User) {
        activity?.let {

        }
    }

    private fun openRegisterFragment() {
        findNavController().navigate(R.id.registerFragment)
    }

}
