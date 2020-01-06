package com.example.myapplication.data.source.local

import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import com.example.myapplication.data.source.UserLocalSource
import javax.inject.Inject


class UserLocalSourceImpl @Inject constructor(
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : UserLocalSource {

    override fun getId(): String? {
        return sharedPreferenceStorage.userId
    }

    override fun cache(userId: String) {
        sharedPreferenceStorage.userId = userId
    }
}
