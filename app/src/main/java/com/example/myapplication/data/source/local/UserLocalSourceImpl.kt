package com.example.myapplication.data.source.local

import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.UserLocalSource

class UserLocalSourceImpl(private val sharedPreferenceStorage: SharedPreferenceStorage) :
    UserLocalSource {
    override fun catchUserId(data: String) {
        sharedPreferenceStorage.userId = data
    }
}