package com.example.myapplication

import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome


val fakeComment = Comment(1, 2, "TEST", "TEST", "TEST")
val fakeCommentList = listOf(fakeComment, fakeComment, fakeComment, fakeComment, fakeComment)
val throwable = Throwable()
val successFullOutcome = Outcome.Success(fakeCommentList)
val failOutcome = Outcome.Failure<List<Comment>>("Error",throwable)