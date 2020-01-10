package com.example.myapplication.data.source

import com.example.myapplication.domain.entity.UserSession

interface IUserSessionLocalSource {

    fun get(): UserSession

    fun create(userSession: UserSession)

    fun update(userSession: UserSession)
}
