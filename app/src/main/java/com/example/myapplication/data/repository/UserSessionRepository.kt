package com.example.myapplication.data.repository

import com.example.myapplication.data.source.usersession.local.UserSessionLocalSource
import com.example.myapplication.domain.entity.UserSession
import com.example.myapplication.domain.repository.IUserSessionRepository
import javax.inject.Inject

class UserSessionRepository
@Inject constructor(
    private val userSessionLocalSource: UserSessionLocalSource
) : IUserSessionRepository {

    override suspend fun create(userSession: UserSession) {
        userSessionLocalSource.create(userSession)
    }

    override suspend fun get(): UserSession {
        return userSessionLocalSource.get()
    }

    override suspend fun update(userSession: UserSession) {
        userSessionLocalSource.update(userSession)
    }
}
