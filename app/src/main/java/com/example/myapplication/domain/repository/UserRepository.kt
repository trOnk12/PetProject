package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.User

interface UserRepository {
    suspend fun get(id: String): User
    suspend fun create(user: User)
    suspend fun update(user: User)
}
