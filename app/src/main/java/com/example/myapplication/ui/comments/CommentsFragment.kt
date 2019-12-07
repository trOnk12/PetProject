package com.example.myapplication.ui.comments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.core.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import dagger.android.support.AndroidSupportInjection
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

        val binding = CommentsFragmentBinding.inflate(inflater, container, false).apply {
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


//        viewModel.comments.observe(this, Observer { commentList -> renderCommentList(commentList) })
//        viewModel.failure.observe(
//            this,
//            Observer { failure -> failure.getContentIfNotHandled()?.let { handleFailure(it) } })
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
//    private fun handleFailure(failure: Failure) {
//        when (failure) {
//            is Failure.ServerError -> Log.d("TEST", "failure test")
//        }
//    }
//
//    private fun renderCommentList(comments: List<Comment>) {
//        CoroutineScope(Dispatchers.Main).launch { commentAdapter.updateData(comments) }
//    }

}
