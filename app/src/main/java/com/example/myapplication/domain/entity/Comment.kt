package com.example.myapplication.domain.entity

data class Comment(
    val userId: Int,
    val id: String,
    val title: String?,
    val body: String,
    val isFavourite: Boolean = false
) {

    fun toggleIsFavourite(): Comment {
        return this.copy(isFavourite = !isFavourite)
    }
}
