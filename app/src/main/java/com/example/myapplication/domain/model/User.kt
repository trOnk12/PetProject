package com.example.myapplication.domain.model

data class User(
    val id: String,
    val name: String,
    val favouriteComments: ArrayList<String> = arrayListOf()
){

    fun addCommentToFavourite(id:String): User {
        favouriteComments.add(id)
        return this
    }

}


