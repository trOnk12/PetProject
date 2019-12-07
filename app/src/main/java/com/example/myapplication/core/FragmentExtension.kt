package com.example.myapplication.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


//Method that returns the view model specified by the requested type, thanks to reified and inline
inline fun <reified T : ViewModel> Fragment.viewModel(provider: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, provider).get(T::class.java)
}