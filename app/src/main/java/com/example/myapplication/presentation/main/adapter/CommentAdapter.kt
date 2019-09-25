package com.example.myapplication.presentation.main.adapter

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.entity.Comment
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.example.myapplication.databinding.CommentItemViewBinding
import com.example.myapplication.presentation.utils.BindableAdapter

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(), BindableAdapter<List<Comment>> {

    lateinit var commentList: List<Comment>

    override fun setData(data: List<Comment>) {
        commentList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CommentItemViewBinding = DataBindingUtil.inflate(
            layoutInflater, viewType, parent, false
        )
        return CommentViewHolder(binding)
    }

    override fun getItemCount() = commentList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    class CommentViewHolder(var binding: CommentItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.apply{
                setVariable(BR.item,comment)
                executePendingBindings()
            }
        }

    }

}