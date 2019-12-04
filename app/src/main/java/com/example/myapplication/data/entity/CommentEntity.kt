package com.example.myapplication.data.entity

import com.example.myapplication.domain.model.Comment
import com.google.gson.annotations.SerializedName

data class CommentEntity(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)

fun CommentEntity.mapToDomain(): Comment = Comment(userId, id, title, body)
fun List<CommentEntity>.mapToDomain(): List<Comment> = map { it.mapToDomain() }
