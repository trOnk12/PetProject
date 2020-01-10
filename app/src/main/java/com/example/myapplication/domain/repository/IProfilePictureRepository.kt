package com.example.myapplication.domain.repository

interface IProfilePictureRepository {

    fun upload(uri: String): String
}
