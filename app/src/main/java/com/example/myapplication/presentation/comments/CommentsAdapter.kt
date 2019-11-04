package com.example.myapplication.presentation.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Comment
import kotlinx.android.synthetic.main.comment_item_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    var comments: List<Comment> = emptyList()

    internal var clickListener: (commentItem: Comment) -> Unit = { _ -> }

    suspend fun updateData(newValues: List<Comment>) {
        calculateDiff(newValues)
    }

    private suspend fun calculateDiff(newValues: List<Comment>) {
        withContext(Dispatchers.IO) {
            val diffResult = calculateDiff(newValues,comments)

            withContext(Dispatchers.Main) {
                comments = newValues
                diffResult.dispatchUpdatesTo(this@CommentsAdapter)
            }
        }
    }

    private fun calculateDiff(oldValue: List<Comment>, newValue: List<Comment>): DiffUtil.DiffResult {
        val diffCallBack = CommentDiffCallBack(oldValue, newValue)
        return DiffUtil.calculateDiff(diffCallBack)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
        viewHolder.bind(comments[p1], clickListener)
    }

    override fun getItemCount() = comments.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(commentItem: Comment, clickListener: (Comment) -> Unit) {
            itemView.apply {
                title.text = commentItem.title
                body.text = commentItem.body
                favouriteIcon.setOnClickListener { clickListener }
            }
        }
    }

}
