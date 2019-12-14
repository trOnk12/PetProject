package com.example.myapplication.data.source.local

import com.example.core.functional.Either
import com.example.myapplication.data.local.sharedpreferences.SharedPreferenceStorage
import javax.inject.Inject

class CommentLocalSource
@Inject constructor(
    private val sharedPreferenceStorage: SharedPreferenceStorage
) {

    fun addToFavourite(id: String) =
       sharedPreferenceStorage.addCommentToFavourite(id)

}