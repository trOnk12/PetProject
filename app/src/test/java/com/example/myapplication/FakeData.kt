package com.example.myapplication

import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome
import retrofit2.HttpException


val fakeComment = Comment(1, 2, "TEST", "TEST")
val fakeCommentList = listOf(fakeComment, fakeComment, fakeComment, fakeComment, fakeComment)
val throwable = Exception()
val successFullOutcome = Outcome.Success(fakeCommentList)
val failOutcome = Outcome.Failure("Error",throwable)