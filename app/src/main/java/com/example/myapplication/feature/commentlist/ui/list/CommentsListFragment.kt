package com.example.myapplication.feature.commentlist.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.core.extension.observe
import com.example.myapplication.core.extension.showToast
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.core.platform.BaseFragment
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.feature.commentlist.ui.list.adapter.CommentAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class CommentsListFragment : BaseFragment<CommentsFragmentBinding, CommentsListViewModel>(R.layout.comments_fragment) {

    @Inject
    lateinit var adapter: CommentAdapter

    override fun onInitDataBinding() {
        viewModel = viewModel(provider)

        viewBinding.apply {
            toolbar.toolbarEventListener = this@CommentsListFragment.viewModel
            swipeContainer.setOnRefreshListener { this@CommentsListFragment.viewModel.loadComments() }
            commentList.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            observe(event, ::onViewEvent)
            observe(snackBarMessage, ::onSnackBarMessage)
            observe(comments, ::onCommentsFetched)
            observe(userSession, ::onUserFetched)
            observe(failure, ::onFailure)
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUploadCompletion(updatedUser: User) {
        viewModel.apply {
            userSession.value = updatedUser
        }
    }

    private fun onCommentsFetched(comments: List<Comment>) {
        lifecycleScope.launch {
            adapter.submitList(comments)
        }
    }

    private fun onUserFetched(user: User) {
        viewBinding.toolbar.user = user
    }

    private fun onSnackBarMessage(message: String) =
        Snackbar.make(commentList, message, Snackbar.LENGTH_LONG).show()

    private fun onFailure(exception: Exception) =
        showToast(exception.message)

    private fun onViewEvent(viewState: CommentsListViewEvent) {
        when (viewState) {
            is CommentsListViewEvent.OpenCommentDetail -> openCommentDetail(viewState.comment)
            is CommentsListViewEvent.OpenImageSourceDialog -> openImageSourceDialog()
        }
    }

    private fun openCommentDetail(comment: Comment) {
        findNavController().navigate(
            CommentsListFragmentDirections.actionCommentFragmentToCommentDetailFragment(
                comment.id
            )
        )
    }

    private fun openImageSourceDialog() =
        findNavController().navigate(R.id.optionDialog)

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
