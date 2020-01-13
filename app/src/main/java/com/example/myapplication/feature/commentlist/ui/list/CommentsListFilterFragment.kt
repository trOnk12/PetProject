package com.example.myapplication.feature.commentlist.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R


class CommentsListFilterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comments_filter_fragment, container, false)
    }
//    BaseFragment<CommentsFilterFragmentBinding, CommentsListViewModel>
//        (R.layout.comments_filter_fragment) {
//
//    override fun onInitDependency() {
//        DaggerCommentsListFilterComponent
//            .builder()
//            .commentsListFilterModule(CommentsListFilterModule(this))
//            .coreComponent(PetProject.coreComponent(requireContext()))
//            .build()
//            .inject(this)
//    }
//
//    override fun onInitViewModel() {
//        viewModel = parentViewModelProvider(provider)
//    }
//
//    override fun onInitDataBinding() {
//
//    }

}