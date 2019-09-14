package com.example.myapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome
import com.example.myapplication.domain.usecase.CommentUseCase

class MainActivityViewModel(var commentUseCase: CommentUseCase) : ViewModel() {

    val commentListOutcome = MutableLiveData<Outcome<List<Comment>>>()

    fun fetchComments(){

    }

}