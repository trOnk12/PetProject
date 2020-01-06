package com.example.myapplication.data.repository

import com.example.myapplication.data.source.UserLocalSource
import com.example.myapplication.data.source.UserRemoteSource
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource
) : UserRepository {

    override suspend fun get(id: String): User {
        return userRemoteSource.get(id)
    }

    override suspend fun create(user: User) {
        userRemoteSource.create(user)
    }

    override suspend fun update(user: User) {
        userRemoteSource.update(user)
    }

}
