package com.example.myapplication.data.model

import com.example.myapplication.domain.entity.Comment
import com.google.gson.annotations.SerializedName

data class DataComment(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)

fun DataComment.mapToDomain(): Comment = Comment(userId, id, title, body)

fun List<DataComment>.mapToDomain(): List<Comment> = map { it.mapToDomain() }
