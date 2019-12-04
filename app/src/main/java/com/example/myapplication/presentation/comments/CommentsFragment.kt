package com.example.myapplication.presentation.comments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CommentsFragment : BaseFragment() {

    private val navigator: Navigator by inject()
    private val commentAdapter: CommentsAdapter by lazy {
        viewModel.fetchComments()
        CommentsAdapter()
    }

    private val viewModel: CommentsActivityViewModel by viewModel()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()

        viewModel.comments.observe(this, Observer { commentList -> renderCommentList(commentList) })
        viewModel.failure.observe(
            this,
            Observer { failure -> failure.getContentIfNotHandled()?.let { handleFailure(it) } })
    }

    private fun initializeView() {
        commentList.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = commentAdapter
        }

        commentAdapter.clickListener = { comment ->
            navigator.showCommentDetails(activity!!, comment)
        }

        swipeContainer.setOnRefreshListener { viewModel.fetchComments() }
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
