package com.example.myapplication.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.interactor.UseCase.None
import com.example.myapplication.R
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.usecase.AddCommentToFavourite
import com.example.myapplication.domain.usecase.GetComments
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.core.functional.Result

class CommentViewModel @Inject constructor(
    private val getComments: GetComments,
    private val addCommentToFavourite: AddCommentToFavourite
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
            getComments(None()) {
                when (it) {
                    is Result.Success -> handleComments(it.data)
                }
            }
        }
    }

    override fun addToFavourite(comment: Comment) {
        viewModelScope.launch {
            addCommentToFavourite(comment.id.toString()) {
                when (it) {
                    is Result.Success -> addFavouriteSuccess(it.data)
                }
            }
        }
    }

    private fun addFavouriteSuccess(favouriteStatus: FavouriteStatus) {
        val stringResId = if (favouriteStatus == FavouriteStatus.IS_ADDED) {
            R.string.comment_favourite
        } else {
            R.string.comment_unfavourite
        }
        _snackBarEvent.value = Event(stringResId)
    }

    override fun openCommentDetail(comment: Comment) {
        _navigateToCommentDetail.value = Event(comment.id.toString())
    }


    private fun handleComments(comments: List<Comment>) {
        _isRefreshing.value = false
        _comments.value = comments
    }

}

