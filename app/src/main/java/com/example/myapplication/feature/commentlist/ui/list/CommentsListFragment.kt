package com.example.myapplication.feature.commentlist.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.PetProject
import com.example.myapplication.R
import com.example.myapplication.core.commons.base.BaseFragment
import com.example.myapplication.core.extensions.*
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.feature.commentlist.di.CommentListModule
import com.example.myapplication.feature.commentlist.di.DaggerCommentListComponent
import com.example.myapplication.feature.commentlist.ui.list.adapter.CommentAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.comments_fragment.*
import javax.inject.Inject

class CommentsListFragment :
    BaseFragment<CommentsFragmentBinding, CommentsListViewModel>(
        layoutId = R.layout.comments_fragment
    ) {

    @Inject
    lateinit var adapter: CommentAdapter

    private lateinit var bottomSheetView: View
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetView = view.findViewById(R.id.filterBottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView)
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
        viewModel.apply {
            observe(comments) { adapter.submitList(it) }
            observe(event, ::onViewEvent)
        }
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.toolbar.toolbarEventListener = viewModel
        viewBinding.swipeContainer.setOnRefreshListener(viewModel::loadComments)
        viewBinding.filterFab.setOnClickListener {
            if (bottomSheetBehavior.isExpanded()) {
                bottomSheetBehavior.hide()
            } else {
                bottomSheetBehavior.expand()
            }
        }
        viewBinding.commentList.adapter = adapter
    }

    private fun onViewEvent(event: CommentsListViewEvent) {
        when (event) {
            is CommentsListViewEvent.OpenCommentDetail -> navigateToCommentDetail(event.comment)
            is CommentsListViewEvent.OpenImageSourceDialog -> navigateToImageSourceDialog()
            is CommentsListViewEvent.ShowSnackBarMessage -> showSnackBar(commentList, event.message)
        }
    }

    private fun navigateToCommentDetail(comment: Comment) {
        findNavController().navigate(
            CommentsListFragmentDirections.actionCommentFragmentToCommentDetailFragment(
                comment.id
            )
        )
    }

    private fun navigateToImageSourceDialog() {
        findNavController().navigate(
            R.id.optionDialog
        )
    }

}
