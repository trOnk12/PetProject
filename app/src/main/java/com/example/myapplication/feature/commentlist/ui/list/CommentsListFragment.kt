package com.example.myapplication.feature.commentlist.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PetProject
import com.example.myapplication.R
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.core.extensions.observe
import com.example.myapplication.core.extensions.showSnackBar
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.feature.commentlist.di.CommentListModule
import com.example.myapplication.feature.commentlist.di.DaggerCommentListComponent
import com.example.myapplication.feature.commentlist.ui.list.adapter.CommentAdapter
import kotlinx.android.synthetic.main.comments_fragment.*
import javax.inject.Inject

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
            observe(event, ::onViewEvent)
        }
    }

    override fun onInitDependency() {
        DaggerCommentListComponent
            .builder()
            .commentListModule(CommentListModule(this))
            .coreComponent(PetProject.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun onInitViewModel() {
        viewModel = viewModel(provider)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.toolbar.toolbarEventListener = viewModel
        viewBinding.swipeContainer.setOnRefreshListener(viewModel::loadComments)
        viewBinding.commentList.adapter = adapter
    }

    private fun onViewEvent(event: CommentsListViewEvent) {
        when (event) {
            is CommentsListViewEvent.OpenCommentDetail -> findNavController().navigate(
                CommentsListFragmentDirections.actionCommentFragmentToCommentDetailFragment(
                    event.comment.id
                )
            )
            is CommentsListViewEvent.OpenImageSourceDialog -> findNavController().navigate(R.id.optionDialog)
            is CommentsListViewEvent.ShowSnackBarMessage -> showSnackBar(
                commentList,
                event.message
            )
        }
    }

    private fun onCommentsFetched(comments: List<Comment>) =
        adapter.submitList(comments)
}
