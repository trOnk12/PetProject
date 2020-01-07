package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.entity.UserSession

interface UserSessionRepository {

    suspend fun get(): UserSession

    suspend fun update(userSession: UserSession)

    suspend fun create(userSession: UserSession)

}