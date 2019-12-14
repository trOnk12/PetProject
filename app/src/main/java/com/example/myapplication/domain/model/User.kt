package com.example.myapplication.domain.model

data class User(
    val id: String,
    val name:String,
    val favouriteComment: List<String>? = emptyList()
)