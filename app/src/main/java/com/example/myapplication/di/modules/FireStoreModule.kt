package com.example.myapplication.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class FireStoreModule {

    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideFireBaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}