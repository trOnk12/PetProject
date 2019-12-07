package com.example.myapplication.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val userId: Int,
    val id: Int,
    val title: String?,
    val body: String
) : Parcelable

