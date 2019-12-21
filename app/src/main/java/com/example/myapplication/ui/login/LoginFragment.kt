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
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.data.util.ValidationError
import com.example.myapplication.databinding.LoginFragmentBinding
import com.example.myapplication.domain.model.LoginData
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    lateinit var binding: LoginFragmentBinding

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
        val viewModel: LoginViewModel = viewModel(provider)
        viewModel.apply {
            passwordError.observe(this@LoginFragment, Observer(::handlePasswordError))
            emailError.observe(this@LoginFragment, Observer(::handleEmailError))
        }

        binding.apply {
            binding.lifecycleOwner = this@LoginFragment
            binding.viewModel = viewModel
        }
        binding.signInButton.setOnClickListener { viewModel.logIn() }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun handlePasswordError(validationError: ValidationError?) {
        Log.d("TEST", "handlePasswordError")
    }

    private fun handleEmailError(validationError: ValidationError) {
        Log.d("TEST", "handleEmailError")
    }

}