package com.example.myapplication.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.AddCommentToFavouriteUseCase
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.myapplication.core.extension.replace


class CommentViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentToFavouriteUseCase: AddCommentToFavouriteUseCase
) : BaseViewModel(), CommentEventListener {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _navigateToCommentDetail = MutableLiveData<Event<String>>()
    val navigateToCommentDetail: LiveData<Event<String>>
        get() = _navigateToCommentDetail

    private val _snackBarEvent = MutableLiveData<Event<Int>>()
    val snackBarEvent: LiveData<Event<Int>>
        get() = _snackBarEvent

    fun fetchComments() {
        viewModelScope.launch {
            when (val result = getCommentsUseCase(None())) {
                is Result.Success -> handleSuccess(result.data)
                is Result.Error -> handleFailure(result.exception)
            }
        }
    }

    private fun handleSuccess(comments: List<Comment>) {
        _comments.value = comments
    }

    override fun onStarClicked(comment: Comment) {
        viewModelScope.launch {
            when (val result = addCommentToFavouriteUseCase(comment)) {
                is Result.Success -> handleSuccess(result.data)
                is Result.Error -> handleFailure(result.exception)
            }
        }
    }

    private fun handleSuccess(comment: Comment) {
        val newComment: Comment = comment
            .apply { isFavourite != isFavourite }

        _comments.value = _comments.value
            ?.apply {
                replace(comment, newComment)
            }
    }

    override fun onCommentClicked(comment: Comment) {
        _navigateToCommentDetail.value = Event(comment.id)
    }


}

