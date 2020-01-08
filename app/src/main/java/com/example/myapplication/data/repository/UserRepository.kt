package com.example.myapplication.data.repository

import com.example.myapplication.data.source.RemoteSource
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.UserRepository
import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val remoteSource: RemoteSource
) : UserRepository {

    override suspend fun get(id: String): User {
        return remoteSource.get(id)
    }

    override suspend fun create(user: User) {
        remoteSource.create(user)
    }

    override suspend fun update(user: User): User = remoteSource.update(user)

}
