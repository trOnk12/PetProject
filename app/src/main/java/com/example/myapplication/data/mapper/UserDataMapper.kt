package com.example.myapplication.data.mapper

import com.example.myapplication.core.mapper.Mapper
import com.example.myapplication.domain.entity.User
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserDataMapper @Inject constructor() : Mapper<FirebaseUser, User> {

    override fun map(input: FirebaseUser): User {
        return (User(id = input.uid, name = input.displayName))
    }
}
