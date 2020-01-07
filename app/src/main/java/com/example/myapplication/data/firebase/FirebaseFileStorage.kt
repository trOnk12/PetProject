package com.example.myapplication.data.firebase

import android.net.Uri
import com.google.firebase.storage.StorageReference
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class FirebaseFileStorage
@Inject constructor(
    private val storageReference: StorageReference
) {

    suspend fun uploadFile(uri: Uri, pathString: String) =
        withContext(Dispatchers.IO) {
            suspendCancellableCoroutine<Uri> { continuation ->
                val profilePictureRef = storageReference.child("images/$pathString")
                profilePictureRef.putFile(uri)
                    .addOnSuccessListener {
                        val downloadUri = it.storage.downloadUrl
                        downloadUri.addOnSuccessListener {
                            continuation.resume(it)
                        }
                    }
                    .addOnFailureListener {
                        continuation.resumeWithException(it)
                    }
            }
        }
}
