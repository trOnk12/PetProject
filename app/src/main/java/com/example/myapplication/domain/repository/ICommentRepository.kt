package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.Comment

interface ICommentRepository {

    fun get(id: String): Comment

    fun get(): List<Comment>
}
