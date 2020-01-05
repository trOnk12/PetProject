package com.example.myapplication.feature.commentlist.ui.test

import javax.inject.Provider

class Foo : Provider<String> {
    override fun get(): String {
        return "TEST"
    }
}