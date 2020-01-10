package com.example.myapplication.data.source

import com.example.myapplication.domain.entity.Comment

interface ICommentRemoteSource {

    fun get(): List<Comment>

    fun get(id: String): Comment
}
