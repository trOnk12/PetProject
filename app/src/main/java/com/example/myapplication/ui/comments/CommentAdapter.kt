package com.example.myapplication.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_ui.BindableViewHolder
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentAdapter : RecyclerView.Adapter<BindableViewHolder<Comment>>() {

    lateinit var onAddToFavoriteClickListener: OnAddToFavouriteClickListener

    var comments: List<Comment> = emptyList()

    suspend fun updateData(newValues: List<Comment>) {
        calculateDiff(newValues)
    }

    private suspend fun calculateDiff(newValues: List<Comment>) {
        withContext(Dispatchers.IO) {
            val diffCallBack = CommentDiffCallBack(comments, newValues)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)

            withContext(Dispatchers.Main) {
                comments = newValues
                diffResult.dispatchUpdatesTo(this@CommentAdapter)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BindableViewHolder<Comment> {
        val binding: CommentItemViewBinding =
            DataBindingUtil.inflate(LayoutInflater.from(p0.context), R.layout.comment_item_view, p0, false)

        with(binding) {
            onAddToFavoriteClickListener = this@CommentAdapter.onAddToFavoriteClickListener
        }

        return BindableViewHolder(binding, BR.comment_item)
    }

    override fun onBindViewHolder(p0: BindableViewHolder<Comment>, p1: Int) {
        p0.bind(comments[p1])
    }

    override fun getItemCount() = comments.size

    interface OnAddToFavouriteClickListener {
        fun onAddToFavouriteClick(comment: Comment)
    }

}
