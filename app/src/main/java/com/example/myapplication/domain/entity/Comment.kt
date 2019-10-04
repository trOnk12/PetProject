package com.example.myapplication.domain.entity

data class Comment(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String?=null,
    val body: String?=null
)