package com.example.myapplication.presentation.comments

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.domain.model.Comment

class CommentDiffCallBack(var currentValues: List<Comment>, var newValues: List<Comment>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentValues[oldItemPosition].id == newValues[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return currentValues.size
    }

    override fun getNewListSize(): Int {
        return newValues.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentValues[oldItemPosition] == newValues[newItemPosition]
    }

}