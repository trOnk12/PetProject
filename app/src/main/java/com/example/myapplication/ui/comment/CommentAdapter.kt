package com.example.myapplication.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentAdapter(
    private val commentEventListener: CommentEventListener,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Comment, CommentViewHolder>(CommentDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val commentItemViewBinding =
            CommentItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(commentItemViewBinding, commentEventListener, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CommentViewHolder(
    private val binding: CommentItemViewBinding,
    private val commentEventListener: CommentEventListener,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment) {
        binding.comment = comment
        binding.commentEventListener = commentEventListener
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

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