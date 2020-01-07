package com.example.myapplication.domain.repository

interface ProfilePictureRepository {

    fun upload(uri: String): String

}