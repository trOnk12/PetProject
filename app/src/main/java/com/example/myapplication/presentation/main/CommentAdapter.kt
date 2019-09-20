package com.example.myapplication.presentation.main

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.entity.Comment
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.example.myapplication.databinding.CommentItemViewBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    lateinit var commentList: List<Comment>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CommentItemViewBinding = DataBindingUtil.inflate(
            layoutInflater, viewType, parent, false
        )
        return CommentViewHolder(binding)
    }

    override fun getItemCount() = commentList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.apply {
            bind(commentList[position])
        }
    }

    class CommentViewHolder(var binding: CommentItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.setVariable(BR.item, comment)
            binding.executePendingBindings()
        }

    }

}