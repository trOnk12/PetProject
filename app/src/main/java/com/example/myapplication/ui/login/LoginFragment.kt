package com.example.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.databinding.LoginFragmentBinding
import javax.inject.Inject

class LoginFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: LoginViewModel
    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = viewModel(viewModelFactory)

        binding = LoginFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewModel = viewModel
            }

        return binding.root
    }
}