package com.example.myapplication.feature.commentlist.ui.list.adapter.holder

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.feature.commentlist.ui.list.CommentListListeners

class CommentViewHolder(
    private val binding: CommentItemViewBinding,
    private val commentEventListener: CommentListListeners.CommentEventListener,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment) {
        binding.comment = comment
        binding.commentEventListener = commentEventListener
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}
