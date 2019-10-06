package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.Outcome
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.usecase.CommentUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel constructor(private val commentUseCase: CommentUseCase) : ViewModel() {

     private val _items = MutableLiveData<List<Comment>>()
    val items: LiveData<List<Comment>> = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackBarText = MutableLiveData<String>()
    val snackBarText: LiveData<String> = _snackBarText

    fun fetchComments() {
    //    _isLoading.value = true

        viewModelScope.launch {
            commentUseCase.getComment().let { outcome ->
                when (outcome) {
                    is Outcome.Success -> onCommentsLoaded(outcome.value)
                    is Outcome.Failure -> onFailed(outcome.exception, outcome.message)
                }
            }
        }
      //  _isLoading.value = false
    }

    private fun onCommentsLoaded(comments: List<Comment>) {
        _items.value = comments
    }

    private fun onFailed(exception: Exception, message: String) {
        _snackBarText.value = message
    }

}