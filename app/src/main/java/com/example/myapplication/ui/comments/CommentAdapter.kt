package com.example.myapplication.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.core_ui.BindableViewHolder
import com.example.core_ui.GenericBindableAdapter
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.model.Comment

class CommentAdapter :
    GenericBindableAdapter<Comment>() {

     var onAddToFavoriteClick : (Comment) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<Comment> {
        val binding: CommentItemViewBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.comment_item_view, parent, false)

        return BindableViewHolder(binding, BR.commentItem)
    }

}
