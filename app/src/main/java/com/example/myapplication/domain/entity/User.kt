package com.example.myapplication.domain.entity

data class User(
    val id: String = "",
    val name: String? = "",
    val profileImageUrl: String = "",
    val favouriteCommentsId: ArrayList<String> = arrayListOf()
) {

    fun addCommentToFavourite(id: String): User {
        if (favouriteCommentsId.contains(id)) favouriteCommentsId.remove(id)
        else favouriteCommentsId.add(id)

        return this
    }

    fun updateProfileImageUrl(uri: String): User {
        return this.copy(id = uri)
    }
}
