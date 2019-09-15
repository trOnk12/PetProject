package com.example.myapplication.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}