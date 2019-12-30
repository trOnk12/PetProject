package com.example.myapplication.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentAdapter(
    private val commentEventListener: CommentEventListener,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var comments: List<Comment> = emptyList()

    suspend fun updateData(newValues: List<Comment>) {
        calculateDiff(newValues)
    }

    private suspend fun calculateDiff(newValues: List<Comment>) {
        withContext(Dispatchers.IO) {
            val diffResult = calculateDiff(newValues, comments)

            withContext(Dispatchers.Main) {
                comments = newValues
                diffResult.dispatchUpdatesTo(this@CommentAdapter)
            }
        }
    }

    private fun calculateDiff(
        newValue: List<Comment>,
        oldValue: List<Comment>
    ): DiffUtil.DiffResult {
        val diffCallBack = CommentDiffCallBack(newValue, oldValue)
        return DiffUtil.calculateDiff(diffCallBack)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommentViewHolder {
        val commentItemViewBinding =
            CommentItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(commentItemViewBinding, commentEventListener, lifecycleOwner)
    }

    override fun onBindViewHolder(commentViewHolder: CommentViewHolder, position: Int) {
        commentViewHolder.bind(comments[position])
    }

    override fun getItemCount() = comments.size

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

}

class CommentDiffCallBack(private var newValue: List<Comment>, var oldValue: List<Comment>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newValue[oldItemPosition].id == oldValue[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldValue.size
    }

    override fun getNewListSize(): Int {
        return newValue.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newValue[oldItemPosition] == oldValue[newItemPosition]
    }

}

interface CommentEventListener {

    fun onStarClicked(comment: Comment)

    fun onCommentClicked(comment: Comment)
}