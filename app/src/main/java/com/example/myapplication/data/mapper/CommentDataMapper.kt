package com.example.myapplication.data.mapper

import com.example.myapplication.data.model.DataComment
import com.example.myapplication.domain.entity.Comment

class CommentDataMapper : Mapper<DataComment, Comment> {

    override fun map(input: DataComment): Comment {
        return Comment(
            userId = input.userId,
            id = input.id,
            title = input.title,
            body = input.body
        )
    }

}