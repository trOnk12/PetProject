package com.example.core.interactor

import com.example.core.functional.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    suspend operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) {
        try {
            withContext(Dispatchers.IO) {
                val job = async { run(params) }
                withContext(Dispatchers.Main) {
                    launch { onResult(job.await()) }
                }
            }
        } catch (e: Exception) {
            onResult(Result.Error(e))
        }
    }

    class None
}