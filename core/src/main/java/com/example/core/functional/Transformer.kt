package com.example.core.functional

import com.example.core.exception.Failure
import retrofit2.Call
import java.rmi.ServerError

class Transformer {
    operator fun <T, R> invoke(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform(response.body() ?: default))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}