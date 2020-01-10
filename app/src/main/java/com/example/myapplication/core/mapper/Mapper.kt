package com.example.myapplication.core.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
