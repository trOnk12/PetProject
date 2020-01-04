package com.example.myapplication.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.myapplication.core.Event
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.model.Comment
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.usecase.AddCommentToFavouriteUseCase
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import com.example.myapplication.domain.usecase.GetUserUseCase
import com.example.myapplication.ui.model.NavigationState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentToFavouriteUseCase: AddCommentToFavouriteUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel(), Events.CommentEventListener, Events.ToolBarEventListener {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _navigationState = MutableLiveData<Event<NavigationState>>()
    val navigationState: LiveData<Event<NavigationState>>
        get() = _navigationState

    private val _snackBarMessage = MutableLiveData<Event<String>>()
    val snackBarMessage: LiveData<Event<String>>
        get() = _snackBarMessage

    val userSession = MutableLiveData<User>()

    override fun onCommentClicked(comment: Comment) {
        _navigationState.value = Event(NavigationState.CommentDetail(comment))
    }

    override fun onProfilePictureClicked() {
        _navigationState.value = Event(NavigationState.OptionDialog)
    }

    fun loadUser() {
        viewModelScope.launch {
            when (val result = getUserUseCase(None())) {
                is Result.Success -> handleSuccess(user = result.data)
                is Result.Error -> handleFailure(result.exception)
            }
        }
    }

    private fun handleSuccess(user: User) {
        userSession.value = user
    }

    fun loadComments() {
        viewModelScope.launch {
            when (val result = getCommentsUseCase(None())) {
                is Result.Success -> handleSuccess(comments = result.data)
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
                is Result.Success -> handleSuccess(comment = result.data)
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


}


