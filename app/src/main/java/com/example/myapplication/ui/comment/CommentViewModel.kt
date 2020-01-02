package com.example.myapplication.ui.comment

import android.net.Uri
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
import com.example.myapplication.domain.usecase.UpdateProfilePictureUseCase

class CommentViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentToFavouriteUseCase: AddCommentToFavouriteUseCase,
    private val updateProfilePictureUseCase: UpdateProfilePictureUseCase
) : BaseViewModel(), CommentEventListener, ToolBarEventListener {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _navigationState = MutableLiveData<Event<NavigationState>>()
    val navigationState: LiveData<Event<NavigationState>>
        get() = _navigationState

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
        comment.apply {
            isFavourite = !isFavourite
        }
        _comments.value = _comments.value
    }

    fun uploadProfileImage(chosenImageUri: Uri?) {
        chosenImageUri?.let {
            updateProfilePictureUseCase
        }
    }

    override fun onCommentClicked(comment: Comment) {
        _navigationState.value = Event(NavigationState.CommentDetail(comment))
    }

    override fun onProfilePictureClicked() {
        _navigationState.value = Event(NavigationState.OptionDialog)
    }

}

sealed class NavigationState {
    data class CommentDetail(val comment: Comment) : NavigationState()
    object OptionDialog : NavigationState()
}

interface CommentEventListener {
    fun onStarClicked(comment: Comment)
    fun onCommentClicked(comment: Comment)
}

interface ToolBarEventListener {
    fun onProfilePictureClicked()
}

