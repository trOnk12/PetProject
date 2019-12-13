package com.example.myapplication.ui.comment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.core.exception.Failure
import com.example.myapplication.core.EventObserver
import com.example.myapplication.core.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.model.Comment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CommentViewModel
    private lateinit var binding: CommentsFragmentBinding

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = viewModel(viewModelFactory)

        binding = CommentsFragmentBinding.inflate(inflater, container, false)
            .apply {
                viewModel = viewModel
            }

        viewModel.comments.observe(this, Observer(::renderCommentList))
        viewModel.navigateToCommentDetail.observe(this, EventObserver(::navigateToCommentDetail))
        viewModel.failure.observe(this, EventObserver(::handleFailure))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentAdapter = CommentAdapter(viewModel, this)

        binding.commentList.apply {
            adapter = this@CommentFragment.commentAdapter
        }

        swipeContainer.setOnRefreshListener { viewModel.fetchComments() }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

    private fun navigateToCommentDetail(id: String) {
        openCommentDetail(id)
    }

    private fun openCommentDetail(id: String) {
        val action =
            CommentFragmentDirections.actionCommentFragmentToCommentDetailFragment(id)
        findNavController().navigate(action)
    }

}
