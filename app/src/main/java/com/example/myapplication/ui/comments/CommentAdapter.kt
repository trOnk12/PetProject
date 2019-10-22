package com.example.myapplication.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.core_ui.BindableViewHolder
import com.example.core_ui.GenericBindableAdapter
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.domain.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentAdapter :
    GenericBindableAdapter<Comment>() {

    interface OnAddToFavouriteClickListener {
        fun onAddToFavouriteClick(comment: Comment)
    }

    lateinit var onAddToFavoriteClickListener: OnAddToFavouriteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<Comment> {
        val binding: CommentItemViewBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.comment_item_view, parent, false)

        with(binding) {
            onAddToFavoriteClickListener = this@CommentAdapter.onAddToFavoriteClickListener
        }

        return BindableViewHolder(binding, BR.comment_item)
    }

    suspend fun updateData(newValues: List<Comment>) {
        calculateDiff(currentValues = dataList, newValues = newValues)
    }

    private suspend fun calculateDiff(currentValues: List<Comment>, newValues: List<Comment>) {
        withContext(Dispatchers.IO) {
            val diffCallBack = CommentDiffCallBack(currentValues, newValues)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)

            withContext(Dispatchers.Main) {
                setData(newValues)
                diffResult.dispatchUpdatesTo(this@CommentAdapter)
            }
        }
    }

}
