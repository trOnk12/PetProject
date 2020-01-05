package com.example.myapplication

import com.example.core.Outcome
import com.example.myapplication.domain.entity.Comment

val fakeComment = Comment(1, 2, "TEST", "TEST")
val fakeCommentList = listOf(fakeComment, fakeComment, fakeComment, fakeComment, fakeComment)
val throwable = Exception()
val successFullOutcome = com.example.core.Outcome.Success(fakeCommentList)
val failOutcome = com.example.core.Outcome.Failure("Error", throwable)
