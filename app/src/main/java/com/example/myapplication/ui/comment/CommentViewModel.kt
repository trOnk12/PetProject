package com.example.myapplication.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.interactor.UseCase.None
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.GetComments
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentViewModel @Inject constructor(
    private val getComments: GetComments
) : BaseViewModel(), CommentEventListener{

    private val _items = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _items

    val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    fun fetchComments() {
        viewModelScope.launch {
            getComments(None()) { it.either(::handleFailure, ::handleComments) }
        }
    }

    private fun handleComments(comments: List<Comment>) {
        _isRefreshing.value = false
        _items.value = comments
    }

    override fun addToFavourite(comment: Comment) {

    }

    override fun openCommentDetail(comment: Comment) {

    }

}