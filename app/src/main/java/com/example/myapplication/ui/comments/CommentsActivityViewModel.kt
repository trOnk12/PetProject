package com.example.myapplication.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import com.example.core.interactor.UseCase.None
import com.example.core.utills.Event
import com.example.core_ui.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.GetComments
import kotlinx.coroutines.launch

class CommentsActivityViewModel constructor(
    private val getComments: GetComments
) : BaseViewModel() {

    private val _items = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _items

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    fun fetchComments() {
        viewModelScope.launch {
            getComments(None()) { it.either(::handleFailure, ::handleComments) }
        }
    }

    private fun handleComments(comments: List<Comment>) {
        _isRefreshing.value = true
        _items.value = comments
    }

}