package com.example.myapplication.ui.comments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.exception.Failure
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.ui.route.Navigator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var commentAdapter: CommentsAdapter

    private lateinit var viewModel: CommentsActivityViewModel
    private lateinit var binding: CommentsFragmentBinding

    override fun layoutId(): Int = R.layout.comments_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.comments_fragment, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[CommentsActivityViewModel::class.java]

        viewModel.comments.observe(this, Observer { commentList -> renderCommentList(commentList) })
        viewModel.failure.observe(
            this,
            Observer { failure -> failure.getContentIfNotHandled()?.let { handleFailure(it) } })
    }

    private fun initializeView() {
        commentAdapter = CommentsAdapter()

        commentList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = commentAdapter
        }

        commentAdapter.clickListener = { comment ->
            Navigator.showCommentDetails(activity!!, comment)
        }

        swipeContainer.setOnRefreshListener { viewModel.fetchComments() }

        viewModel.fetchComments()
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> Log.d("TEST", "failure test")
        }
    }

    private fun renderCommentList(comments: List<Comment>) {
        CoroutineScope(Dispatchers.Main).launch { commentAdapter.updateData(comments) }
    }

}
