package com.example.myapplication.feature.commentlist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.myapplication.core.extensions.replace
import com.example.myapplication.core.commons.livedata.SingleLiveData
import com.example.myapplication.core.commons.base.BaseViewModel
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.AddCommentToFavouriteUseCase
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import com.example.myapplication.domain.usecase.GetUserUseCase
import com.example.myapplication.feature.login.ui.LoginViewEvent
import javax.inject.Inject
import kotlinx.coroutines.launch

class CommentsListViewModel
@Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentToFavouriteUseCase: AddCommentToFavouriteUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel(), CommentListListeners.CommentEventListener,
    CommentListListeners.ToolBarEventListener {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _event = SingleLiveData<CommentsListViewEvent>()
    val event: LiveData<CommentsListViewEvent>
        get() = _event

    val userSession = MutableLiveData<User>()

    private fun loadUser() {
        viewModelScope.launch {
            when (val result = getUserUseCase(None())) {
                is Result.Success -> userSession.value = result.data
                is Result.Error -> handleFailure(result.exception)
            }
        }
    }

    fun loadComments() {
        viewModelScope.launch {
            when (val result = getCommentsUseCase(None())) {
                is Result.Success -> _comments.value = result.data
                is Result.Error -> result.exception.message?.let { message ->
                    _event.value = CommentsListViewEvent.ShowSnackBarMessage(message)
                }
            }
        }
    }

    override fun onStarClicked(comment: Comment) {
        viewModelScope.launch {
            when (val result = addCommentToFavouriteUseCase(comment.toggleIsFavourite())) {
                is Result.Success -> handleSuccess(oldComment = comment, newComment = result.data)
                is Result.Error -> result.exception.message?.let { message ->
                    _event.value = CommentsListViewEvent.ShowSnackBarMessage(message)
                }
            }
        }
    }

    private fun handleSuccess(oldComment: Comment, newComment: Comment) {
        _comments.value = _comments.value?.run {
            this.replace(oldComment, newComment)
        }
    }

    override fun onCommentClicked(comment: Comment) {
        _event.value = CommentsListViewEvent.OpenCommentDetail(comment)
    }

    override fun onProfilePictureClicked() {
        _event.value = CommentsListViewEvent.OpenImageSourceDialog
    }
}
