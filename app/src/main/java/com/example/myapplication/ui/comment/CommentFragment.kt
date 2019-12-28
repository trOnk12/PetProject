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
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.model.Comment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentFragment : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    private lateinit var binding: CommentsFragmentBinding

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommentsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val commentViewModel: CommentViewModel = viewModel(provider)
        commentViewModel.apply {
            comments.observe(this@CommentFragment, Observer(::renderCommentList))
            navigateToCommentDetail.observe(this@CommentFragment, EventObserver(this@CommentFragment::navigateToCommentDetail))
            failure.observe(this@CommentFragment, EventObserver(::handleFailure))
            snackBarEvent.observe(this@CommentFragment, EventObserver(::handleSnackBar))
        }

        commentAdapter = CommentAdapter(commentViewModel, this)

        binding.apply {
            commentList.adapter = commentAdapter
            swipeContainer.setOnRefreshListener { commentViewModel.fetchComments() }
        }

        commentViewModel.fetchComments()
    }

    private fun handleFailure(exception: Exception) {

    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun renderCommentList(comments: List<Comment>) {
        CoroutineScope(Dispatchers.Main).launch { commentAdapter.updateData(comments) }
    }

    private fun handleSnackBar(i: Int) {
        Snackbar.make(commentList, i, Snackbar.LENGTH_LONG).show()
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
