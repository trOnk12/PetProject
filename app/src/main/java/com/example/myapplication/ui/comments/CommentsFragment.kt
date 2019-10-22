package com.example.myapplication.ui.comments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.exception.Failure
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.di.injectFeature
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.ui.route.Navigator
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CommentsFragment : BaseFragment(){

    private val navigator: Navigator by inject()
    private val viewModel: CommentsActivityViewModel by viewModel()
    private val commentAdapter: CommentAdapter = CommentAdapter()

     fun stopRefreshing() = refreshStatus(false)

    private fun refreshStatus(status: Boolean) {
        swipeContainer.isRefreshing = status
    }

    override fun layoutId() = R.layout.comments_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()

        viewModel.comments.observe(this, Observer { renderCommentList(it) })
        viewModel.failure.observe(
            this,
            Observer { it.getContentIfNotHandled()?.let { failure -> handleFailure(failure) } })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        viewModel.fetchComments()
    }

    private fun initializeView() {
        commentList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = commentAdapter
        }

        commentAdapter.onAddToFavoriteClickListener = object : CommentAdapter.OnAddToFavouriteClickListener {
            override fun onAddToFavouriteClick(comment: Comment) {
                navigator.showCommentDetails(comment)
            }
        }

        swipeContainer.setOnRefreshListener { swipeAction() }
    }

    private fun swipeAction() {
        viewModel.fetchComments()
    }

    private fun renderCommentList(comments: List<Comment>) {
        stopRefreshing()

        CoroutineScope(Dispatchers.Main).launch { commentAdapter.updateData(comments) }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> Log.d("TEST", "failure test")
        }
    }

}
