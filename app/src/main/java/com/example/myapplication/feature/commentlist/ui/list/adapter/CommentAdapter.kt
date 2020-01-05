package com.example.myapplication.feature.commentlist.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.feature.commentlist.ui.list.CommentListListeners
import com.example.myapplication.feature.commentlist.ui.list.adapter.diffs.CommentDiff
import com.example.myapplication.feature.commentlist.ui.list.adapter.holder.CommentViewHolder

class CommentAdapter(
    private val commentEventListener: CommentListListeners.CommentEventListener,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Comment, CommentViewHolder>(CommentDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val commentItemViewBinding =
            CommentItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(
            commentItemViewBinding,
            commentEventListener,
            lifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
