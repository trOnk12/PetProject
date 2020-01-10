package com.example.myapplication.core.extensions

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.register_fragment.*

// Method that returns the view model specified by the requested type, thanks to reified and inline
inline fun <reified T : ViewModel> Fragment.viewModel(provider: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, provider).get(T::class.java)
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModel(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

fun Fragment.showToast(message: String?) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showSnackBar(view: View, message: String) {
    Snackbar.make(signInButton, message, Snackbar.LENGTH_LONG).show()
}

fun Fragment.startWithFinish(intent: Intent) {
    startActivity(intent)
    activity?.finish()
}
