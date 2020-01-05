package com.example.myapplication.data.source.local

import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.UserLocalSource
import javax.inject.Inject
import javax.inject.Singleton


class UserLocalSourceImpl @Inject constructor(
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : UserLocalSource {

    override fun getUserId(): String? {
        return sharedPreferenceStorage.userId
    }

    override fun cacheUserId(data: String) {
        sharedPreferenceStorage.userId = data
    }
}
