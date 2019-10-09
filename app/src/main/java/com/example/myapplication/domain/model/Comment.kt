package com.example.myapplication.domain.model

data class Comment(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String?=null,
    val body: String?=null
)