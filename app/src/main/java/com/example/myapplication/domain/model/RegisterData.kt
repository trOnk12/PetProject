package com.example.myapplication.domain.model

data class RegisterData(
    var email: String = "",
    var password: String = "",
    var repeatPassword:String ="",
    var userName: String = "",
    var gender: String = ""
    )