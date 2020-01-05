package com.example.myapplication.feature.commentlist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.functional.Result
import com.example.core.interactor.None
import com.example.myapplication.core.extension.replace
import com.example.myapplication.core.livedata.SingleLiveData
import com.example.myapplication.core.platform.BaseViewModel
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.AddCommentToFavouriteUseCase
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import com.example.myapplication.domain.usecase.GetUserUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

class CommentsListViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentToFavouriteUseCase: AddCommentToFavouriteUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel(), CommentListListeners.CommentEventListener,
    CommentListListeners.ToolBarEventListener {
    init {
//        loadComments()
//        loadUser()
    }

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _event = SingleLiveData<CommentsListViewEvent>()
    val event: LiveData<CommentsListViewEvent>
        get() = _event

    private val _snackBarMessage = SingleLiveData<String>()
    val snackBarMessage: LiveData<String>
        get() = _snackBarMessage

    val userSession = MutableLiveData<User>()

    override fun onCommentClicked(comment: Comment) {
        _event.value = CommentsListViewEvent.OpenCommentDetail(comment)
    }

    override fun onProfilePictureClicked() {
        _event.value = CommentsListViewEvent.OpenImageSourceDialog
    }

    private fun loadUser() {
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
            when (val result = getCommentsUseCase(userSession.value)) {
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
                is Result.Success -> handleSuccess(oldComment = comment, newComment = result.data)
                is Result.Error -> handleFailure(result.exception)
            }
        }
    }

    private fun handleSuccess(oldComment: Comment, newComment: Comment) {
        val commentList = _comments.value
        commentList?.replace(oldComment, newComment)

        _comments.value = commentList
    }
}
