package com.example.myapplication.data.authentication

import com.example.myapplication.data.mapper.UserDataMapper
import com.example.myapplication.domain.authentication.Authentication
import com.example.myapplication.domain.entity.User
import com.example.myapplication.domain.usecase.LoginData
import com.example.myapplication.domain.usecase.RegisterData
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class FireBaseAuth
@Inject constructor(
    private val fireBaseAuth: FirebaseAuth,
    private val userDataMapper: UserDataMapper
) : Authentication {

    override suspend fun login(loginData: LoginData) =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<User> { continuation ->
                fireBaseAuth.signInWithEmailAndPassword(loginData.email, loginData.password)
                    .addOnCompleteListener { task ->
                        if (!continuation.isActive) return@addOnCompleteListener
                        if (task.isSuccessful) {
                            task.addOnSuccessListener { result ->
                                result.user?.let { user ->
                                    continuation.resume(userDataMapper.map(user))
                                }
                            }
                        } else {
                            task.exception?.let {
                                continuation.resumeWithException(it)
                            }
                        }
                    }
            }
        }

    override suspend fun register(registerData: RegisterData) =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<User> { continuation ->
                fireBaseAuth.createUserWithEmailAndPassword(registerData.email, registerData.password)
                    .addOnCompleteListener { task ->
                        if (!continuation.isActive) return@addOnCompleteListener
                        if (task.isSuccessful) {
                            task.addOnSuccessListener { result ->
                                result.user?.let { user ->
                                    continuation.resume(userDataMapper.map(user))
                                }
                            }
                        } else {
                            task.exception?.let {
                                continuation.resumeWithException(it)
                            }
                        }
                    }
            }
        }
}
