package com.example.myapplication.domain.entity

data class UserSession(val id: String = "") {

    companion object {
        val EMPTY = UserSession()
    }

}
