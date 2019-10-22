package com.example.myapplication.domain.usecase

import androidx.recyclerview.widget.DiffUtil
import com.example.core.exception.Failure
import com.example.core.functional.Either
import com.example.core.functional.map
import com.example.core.interactor.UseCase
import com.example.core.interactor.UseCase.None
import com.example.myapplication.domain.repository.CommentRepository
import com.example.myapplication.domain.model.Comment

class GetComments(private var commentRepository: CommentRepository) : UseCase<List<Comment>, None>() {

    override suspend fun run(params: None): Either<Failure, List<Comment>> {
        return commentRepository.comments()
    }

     private fun difference(oldValue: List<Comment>, newValue: List<Comment>): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldValue[oldItemPosition].id == newValue[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return oldValue.size
            }

            override fun getNewListSize(): Int {
                return newValue.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldValue[oldItemPosition] == newValue[newItemPosition]
            }
        }
    }

}