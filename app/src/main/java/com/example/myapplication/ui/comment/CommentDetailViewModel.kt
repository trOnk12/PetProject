package com.example.myapplication.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.usecase.GetCommentUseCase
import kotlinx.coroutines.launch
import com.example.core.functional.Result

class CommentDetailViewModel(val getCommentUseCase: GetCommentUseCase) : BaseViewModel() {

    private val _comment = MutableLiveData<Comment>()
    val comment: LiveData<Comment>
        get() = _comment

    fun fetchComment(id: String) {
        viewModelScope.launch {
            when (val result = getCommentUseCase(id)) {
                is Result.Success -> handleComment(result.data)
                is Result.Error -> handleFailure(result.exception)
            }
        }
    }

    private fun handleComment(comment: Comment) {
        _comment.value = comment
    }

}