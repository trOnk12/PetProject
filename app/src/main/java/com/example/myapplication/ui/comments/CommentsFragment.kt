package com.example.myapplication.ui.comments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.di.injectFeature
import com.example.myapplication.ui.route.Navigator
import kotlinx.android.synthetic.main.activity_comment_list.*
import org.koin.android.ext.android.inject

class CommentsFragment : BaseFragment() {

    private val navigator: Navigator by inject()
    private lateinit var commentAdapter: CommentAdapter

    private val viewModel: CommentsActivityViewModel by viewModel()

    override fun layoutId() = R.layout.comments_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeature()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        viewModel.fetchComments()
    }

    private fun initializeView() {
        commentAdapter = CommentAdapter()
        commentAdapter.onAddToFavoriteClick = ({ comment -> navigator.showCommentDetails(comment) })

        commentList.layoutManager = LinearLayoutManager(activity)
        commentList.adapter = commentAdapter
    }

}
