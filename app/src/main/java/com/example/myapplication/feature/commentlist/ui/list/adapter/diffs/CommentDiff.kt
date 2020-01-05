package com.example.myapplication.feature.commentlist.ui.list.adapter.diffs

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.entity.Comment

object CommentDiff : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(
        oldItem: Comment,
        newItem: Comment
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}
