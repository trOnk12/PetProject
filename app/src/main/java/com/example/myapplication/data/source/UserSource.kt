package com.example.myapplication.data.source

import android.net.Uri
import com.example.core.functional.Result
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData

interface RemoteSource {
    suspend fun get(userId: String): User
    suspend fun update(user: User): User
    suspend fun create(user: User): User
}
