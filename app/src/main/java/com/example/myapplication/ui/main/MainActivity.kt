package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.model.Comment
import com.example.core_ui.platform.BaseActivity
import com.example.myapplication.di.injectFeature

class MainActivity : BaseActivity(), CommentAdapter.OnAddToFavoriteClickListener {

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_lis)
        injectFeature()
        initViewComponents()

        viewModel.fetchComments()
    }

    private fun initViewComponents() {
        initViewModel()
        initRecyclerView()
        initSwipeContainer()
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_lis)
        binding.viewModel = this.viewModel

        viewModel.failure.observe(this, Observer {
            it.getContentIfNotHandled()?.let { failure ->
                when (failure) {
                    is Failure.ServerError -> showSnackbar(getString(R.string.server_error_message))
                }
            }
        })

        viewModel.items.observe(this, Observer { commentList ->
            commentAdapter.setData(commentList)
        })

        viewModel.isRefreshing.observe(this, Observer { refreshEvent ->
            refreshEvent.getContentIfNotHandled()?.let { refreshState ->
                if (refreshState) successfulRefresh()
            }
        })
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter(onAddToFavoriteClickListener = this)

        recyclerView = findViewById<RecyclerView>(R.id.commentList).apply {
            layoutManager = viewManager
            adapter = commentAdapter
        }
    }

    private fun initSwipeContainer() {
        swipeContainer.setOnRefreshListener {
            commentAdapter.clearData()
            viewModel.fetchComments()
        }
    }

    private fun successfulRefresh() {
        swipeContainer.isRefreshing = false
    }

    override fun onAddToFavouriteClick(comment: Comment) {
        Log.d("TEST", comment.body)
    }

}
