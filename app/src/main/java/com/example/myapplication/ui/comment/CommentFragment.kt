package com.example.myapplication.ui.comment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.EventObserver
import com.example.myapplication.core.extension.showToast
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.model.User
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class CommentFragment : Fragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory
    private lateinit var binding: CommentsFragmentBinding
    private lateinit var viewModel: CommentViewModel

    private lateinit var adapter: CommentAdapter

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

        viewModel = viewModel(provider)

        viewModel.apply {
            navigationState.observe(this@CommentFragment, EventObserver(::navigate))
            snackBarMessage.observe(this@CommentFragment, EventObserver(::showSnackBar))
            comments.observe(this@CommentFragment, Observer(::renderComments))
            user.observe(this@CommentFragment, Observer(::renderUser))
            failure.observe(this@CommentFragment, EventObserver(::processFailure))
        }

        adapter = CommentAdapter(viewModel, this)

        binding.apply {
            toolbar.toolBarEventListener = this@CommentFragment.viewModel
            swipeContainer.setOnRefreshListener { this@CommentFragment.viewModel.loadComments() }
            commentList.adapter = adapter
        }

        viewModel.loadComments()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUploadCompletion(updatedUser: User) {
        viewModel.apply {
            user.value = updatedUser
        }
    }

    private fun renderComments(comments: List<Comment>) {
        lifecycleScope.launch {
            adapter.submitList(comments)
        }
    }

    private fun renderUser(user: User) {
        binding.toolbar.user = user
    }

    private fun showCommentDetail(comment: Comment) {
        CommentFragmentDirections.actionCommentFragmentToCommentDetailFragment(comment.id).also { action ->
            findNavController().navigate(action)
        }
    }

    private fun showImageSourceDialog() = findNavController().navigate(R.id.optionDialog)

    private fun showSnackBar(message: String) = Snackbar.make(commentList, message, Snackbar.LENGTH_LONG).show()

    private fun processFailure(exception: Exception) = showToast(exception.message)

    private fun navigate(navigationState: NavigationState) {
        when (navigationState) {
            is NavigationState.CommentDetail -> showCommentDetail(navigationState.comment)
            is NavigationState.OptionDialog -> showImageSourceDialog()
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

}
