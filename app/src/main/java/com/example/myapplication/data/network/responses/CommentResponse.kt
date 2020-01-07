package com.example.myapplication.data.network.responses

import com.example.myapplication.domain.entity.Comment
import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("id") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String
)

fun CommentDto.mapToDomain(): Comment = Comment(userId, id.toString(), title, body)
fun List<CommentDto>.mapToDomain(): List<Comment> = map { it.mapToDomain() }
