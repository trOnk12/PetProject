package com.example.myapplication.core.extension

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

//Method that returns the view model specified by the requested type, thanks to reified and inline
inline fun <reified T : ViewModel> Fragment.viewModel(provider: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, provider).get(T::class.java)
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

fun Fragment.showToast(message: String?) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}
