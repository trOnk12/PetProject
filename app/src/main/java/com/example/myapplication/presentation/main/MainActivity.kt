package com.example.myapplication.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.presentation.BaseActivity
import com.example.myapplication.presentation.main.adapter.CommentAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by viewModel()
    private val commentAdapter : CommentAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
    }

    private fun initRecyclerView() {

    }

    private fun initViewModel() {
        viewModel.snackBarText.observe(this, Observer { message ->
            showSnackbar(message)
        })
    }

}
