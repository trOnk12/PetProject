package com.example.myapplication.data.source

import com.example.myapplication.domain.entity.User

interface IUserRemoteSource {

    suspend fun get(userId: String): User

    suspend fun update(user: User): User

    suspend fun create(user: User): User
}
