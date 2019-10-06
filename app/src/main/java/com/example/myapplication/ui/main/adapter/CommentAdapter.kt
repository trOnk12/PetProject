package com.example.myapplication.ui.main.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.entity.Comment
import com.example.core_ui.GenericBindableAdapter
import com.example.myapplication.BR

class CommentAdapter : GenericBindableAdapter<Comment, CommentItemViewBinding>
        (R.layout.comment_item_view, BR.comment_item)
