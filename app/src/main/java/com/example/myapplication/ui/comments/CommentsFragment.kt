package com.example.myapplication.ui.comments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.core.exception.Failure
import com.example.myapplication.core.EventObserver
import com.example.myapplication.core.extension.observe
import com.example.myapplication.core.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.model.Comment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CommentsActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModel(viewModelFactory)

        val binding = CommentsFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewModel = viewModel
            }

        return binding.root
    }

    //
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initializeView()
//    }
//
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.comments.observe(this, Observer(::renderCommentList))
        viewModel.failure.observe(this, EventObserver(::handleFailure))
    }

    //
//    private fun initializeView() {
//        commentAdapter = CommentsAdapter()
//
//        commentList.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = commentAdapter
//        }
//
//        commentAdapter.clickListener = { comment ->
//            Navigator.showCommentDetails(activity!!, comment)
//        }
//
//        swipeContainer.setOnRefreshListener { viewModel.fetchComments() }
//
//        viewModel.fetchComments()
//    }
//
    private fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> Log.d("TEST", "failure test")
        }
    }

    private fun renderCommentList(comments: List<Comment>) {
        CoroutineScope(Dispatchers.Main).launch { commentAdapter.updateData(comments) }
    }

}
