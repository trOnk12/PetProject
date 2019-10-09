package com.example.core.interactor

import com.example.core.exception.Failure
import com.example.core.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        withContext(Dispatchers.IO) {
            val job = async { run(params) }
            withContext(Dispatchers.Main) {
                launch { onResult(job.await()) }
            }
        }
    }

    class None
}