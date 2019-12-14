package com.example.core.exception

import java.lang.Exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    data class SignInFailure(val exception: Exception) : Failure()

}