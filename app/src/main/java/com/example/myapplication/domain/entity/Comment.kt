package com.example.myapplication.domain.entity

data class Comment(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)