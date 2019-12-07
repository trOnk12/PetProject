package com.example.myapplication.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.GetComment
import kotlinx.coroutines.launch

class CommentDetailViewModel(val getComment: GetComment) : BaseViewModel() {

    private val _comment = MutableLiveData<Comment>()
    val comment: LiveData<Comment>
        get() = _comment

    fun getComment(id: String) {
        viewModelScope.launch {
            getComment(id) { it.either(::handleFailure, ::handleComment) }
        }
    }

    private fun handleComment(comment: Comment) {
        _comment.value = comment
    }

}