package com.example.myapplication.domain.model

data class Comment(
    val userId: Int,
    val id: String,
    val title: String?,
    val body: String,
    var isFavourite : Boolean = false
)

