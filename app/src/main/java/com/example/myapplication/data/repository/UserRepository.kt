package com.example.myapplication.data.repository

import com.example.myapplication.data.source.IUserRemoteSource
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository
@Inject constructor(
    private val IUserRemoteSource: IUserRemoteSource
) : IUserRepository {

    override suspend fun get(id: String): User {
        return IUserRemoteSource.get(id)
    }

    override suspend fun create(user: User) {
        IUserRemoteSource.create(user)
    }

    override suspend fun update(user: User): User = IUserRemoteSource.update(user)
}
