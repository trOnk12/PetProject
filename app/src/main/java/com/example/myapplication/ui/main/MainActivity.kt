package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core_ui.GenericBindableAdapter
import com.example.myapplication.R
import com.example.myapplication.ui.BaseActivity
import com.example.myapplication.ui.main.adapter.CommentAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.w3c.dom.Comment

class MainActivity : BaseActivity(){
    private val binding: MainActivityViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewComponents()

        binding.fetchComments()
    }

    private fun initViewComponents() {
        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)

        commentAdapter.setOnItemClickListener(object:GenericBindableAdapter.OnItemClickListener{
            override fun onItemClick(item: Any?) {
                val comment = item as Comment
            }
        })

        recyclerView = findViewById<RecyclerView>(R.id.comment_list).apply {
            layoutManager = viewManager
            adapter = commentAdapter
        }
    }

    private fun initViewModel() {
        binding.snackBarText.observe(this, Observer { message ->
            showSnackbar(message)
        })

        binding.items.observe(this, Observer { commentList ->
            commentAdapter.setData(commentList)
        })
    }

}
