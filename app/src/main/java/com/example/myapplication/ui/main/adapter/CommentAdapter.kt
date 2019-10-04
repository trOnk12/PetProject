package com.example.myapplication.ui.main.adapter

import com.example.myapplication.R
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.entity.Comment
import com.example.ui.GenericBindableAdapter

class CommentAdapter : com.example.ui.GenericBindableAdapter<Comment, CommentItemViewBinding>(R.layout.comment_item_view)
