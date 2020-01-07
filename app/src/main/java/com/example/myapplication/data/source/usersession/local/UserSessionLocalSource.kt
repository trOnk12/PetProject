package com.example.myapplication.data.source.usersession.local

import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.LocalSource
import com.example.myapplication.domain.entity.UserSession
import javax.inject.Inject

class UserSessionLocalSource
@Inject constructor(
    private val userSharedPreferenceStorage: SharedPreferenceStorage.UserSharedPreferenceStorage
) : LocalSource {

    override fun create(userSession: UserSession) {
        userSharedPreferenceStorage.userId = userSession.id
    }

    override fun update(userSession: UserSession) {
        userSharedPreferenceStorage.userId = userSession.id
    }

    override fun get(): UserSession {
        val userId = userSharedPreferenceStorage.userId
        if (userId == null) throw Exception("No user cached") else return UserSession(id = userId)
    }

}