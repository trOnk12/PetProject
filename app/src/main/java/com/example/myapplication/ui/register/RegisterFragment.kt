package com.example.myapplication.ui.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.Event
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.RegisterFragmentBinding
import com.example.myapplication.domain.model.User
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.register_fragment.*
import javax.inject.Inject

class RegisterFragment : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val registerViewModel: RegisterViewModel = viewModel(provider)
        registerViewModel.apply {
            passwordError.observe(this@RegisterFragment, Observer(::handlePasswordError))
            repeatPasswordError.observe(this@RegisterFragment, Observer(::handleRepeatPasswordError))
            emailError.observe(this@RegisterFragment, Observer(::handleEmailError))
            snackBarMessage.observe(this@RegisterFragment, Observer(::showSnackBar))
        }

        binding.apply {
            viewModel = registerViewModel
            lifecycleOwner = this@RegisterFragment
            signInButton.setOnClickListener { registerViewModel.register() }
            registerInfo.setOnClickListener { openLoginFragment() }
        }

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun showSnackBar(event: Event<String>?) {
        event?.let {
            Snackbar.make(signInButton, it.getContentIfNotHandled().toString(), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleEmailError(validationError: ValidationError) {
        when (validationError) {
            is ValidationError.IsEmpty -> emailAddress.error = "Empty password"
        }
    }

    private fun handlePasswordError(validationError: ValidationError?) {
        when (validationError) {
            is ValidationError.IsEmpty -> password.error =" Empty password"
        }
    }

    private fun handleRepeatPasswordError(validationError: ValidationError?) {
        when (validationError) {
            is ValidationError.IsEmpty -> repeatPassword.error = "Empty password"
        }
    }

    private fun openLoginFragment() {
        findNavController().navigate(R.id.loginFragment)
    }


}

