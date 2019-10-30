package com.example.myapplication.presentation.comments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.exception.Failure
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.di.injectFeature
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.presentation.route.Navigator
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CommentsFragment : BaseFragment() {
    override fun layoutId() = R.layout.comments_fragment

    private val navigator: Navigator by inject()
    private val commentsAdapter: CommentsAdapter by lazy {
        viewModel.fetchComments()
        CommentsAdapter()
    }

    private val viewModel: CommentsActivityViewModel by viewModel()
    lateinit var binding: CommentsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.comments_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()
        startObserving()
    }

    private fun initializeView() {
        commentList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = commentsAdapter
        }
        commentsAdapter.clickListener = { comment ->
            viewModel.addToFavourite(comment)
        }
        swipeContainer.setOnRefreshListener{ viewModel.fetchComments() }
    }

    private fun startObserving() {
        viewModel.comments.observe(this, Observer { showComments(it) })
        viewModel.failure.observe(
            this, Observer { it.getContentIfNotHandled()?.let { failure -> handleFailure(failure) } })
    }

    private fun showComments(comments: List<Comment>) {
        CoroutineScope(Dispatchers.Main).launch { commentsAdapter.updateData(comments) }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> Log.d("TEST", "failure test")
        }
    }

}
