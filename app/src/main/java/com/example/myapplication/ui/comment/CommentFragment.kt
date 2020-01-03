package com.example.myapplication.ui.comment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.EventObserver
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.model.Comment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_fragment.*
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
            navigationState.observe(this@CommentFragment, EventObserver(this@CommentFragment::handleNavigationState))
            failure.observe(this@CommentFragment, EventObserver(::handleFailure))
            snackBarMessage.observe(this@CommentFragment, EventObserver(::handleSnackBar))
        }

        commentAdapter = CommentAdapter(commentViewModel, this)

        binding.apply {
            commentList.adapter = commentAdapter
            toolbar.toolBarEventListener = commentViewModel
            swipeContainer.setOnRefreshListener { commentViewModel.fetchComments() }
        }

        commentViewModel.fetchComments()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun renderCommentList(comments: List<Comment>) {
        lifecycleScope.launch {
            commentAdapter.submitList(comments)
        }
    }

    private fun handleFailure(exception: Exception) {
        Toast.makeText(activity, exception.message, Toast.LENGTH_LONG).show()
    }

    private fun handleSnackBar(message: String) {
        Log.d("TEST","testtesttesttesttest")
        Snackbar.make(commentList, message, Snackbar.LENGTH_LONG).show()
    }

    private fun handleNavigationState(navigationState: NavigationState) {
        when (navigationState) {
            is NavigationState.CommentDetail -> navigateToCommentDetail(navigationState.comment)
            is NavigationState.OptionDialog -> navigateToOptionDialog()
        }
    }

    private fun navigateToCommentDetail(comment: Comment) {
        val action =
            CommentFragmentDirections.actionCommentFragmentToCommentDetailFragment(comment.id)
        findNavController().navigate(action)
    }

    private fun navigateToOptionDialog() {
        findNavController().navigate(R.id.optionDialog)
    }

}
