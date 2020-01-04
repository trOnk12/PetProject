package com.example.myapplication.core.extension

import com.example.myapplication.domain.entity.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.mapToDomain(): User {
    return User(uid, displayName.toString())
}