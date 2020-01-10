package com.example.myapplication.feature.commentlist.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.showToast
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.feature.commentlist.ui.list.adapter.CommentAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kotlinx.android.synthetic.main.comments_fragment.*
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CommentsListFragment :
    BaseFragment<CommentsFragmentBinding, CommentsListViewModel>(
        layoutId = R.layout.comments_fragment
    ) {

    @Inject
    lateinit var adapter: CommentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            observe(comments, ::onCommentsFetched)
            observe(userSession, ::onUserFetched)
            observe(event, ::onViewEvent)
            observe(failure, ::onFailure)
        }
    }

    override fun onInitDependency() {

    }

    override fun onInitViewModel() {
        viewModel = viewModel(provider)
    }

    override fun onInitDataBinding() {
        viewBinding.apply {
            toolbar.toolbarEventListener = this@CommentsListFragment.viewModel
            swipeContainer.setOnRefreshListener { this@CommentsListFragment.viewModel.loadComments() }
            commentList.adapter = adapter
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

    private fun showSnackBarMessage(message: String) =
        Snackbar.make(commentList, message, Snackbar.LENGTH_LONG).show()

    private fun onFailure(exception: Exception) =
        showToast(exception.message)

    private fun onViewEvent(viewState: CommentsListViewEvent) {
        when (viewState) {
            is CommentsListViewEvent.OpenCommentDetail -> openCommentDetail(viewState.comment)
            is CommentsListViewEvent.OpenImageSourceDialog -> openImageSourceDialog()
            is CommentsListViewEvent.ShowSnackBarMessage -> showSnackBarMessage(viewState.message)
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
