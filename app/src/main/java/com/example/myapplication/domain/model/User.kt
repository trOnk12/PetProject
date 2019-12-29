package com.example.myapplication.domain.model

data class User(
    val id: String = "",
    val name: String = "",
    val favouriteComments: ArrayList<String> = arrayListOf()
) {

    fun addCommentToFavourite(id: String): User {
        if (favouriteComments.contains(id)) favouriteComments.remove(id)
        else favouriteComments.add(id)

        return this
    }

}


