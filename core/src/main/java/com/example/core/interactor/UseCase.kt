package com.example.core.interactor

import com.example.core.functional.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Result<Type> {
        return try {
            withContext(Dispatchers.IO) {
                run(params).let {
                    Result.Success(it)
                }
            }
        } catch (exception: Exception) {
            return Result.Error(exception)
        }
    }
}
