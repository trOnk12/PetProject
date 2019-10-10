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

    override fun diffCallBack(oldValue: List<Comment>, newValue: List<Comment>): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldValue[oldItemPosition].id == newValue[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return oldValue.size
            }

            override fun getNewListSize(): Int {
                return newValue.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldValue[oldItemPosition] == newValue[newItemPosition]
            }
        }
    }

}
