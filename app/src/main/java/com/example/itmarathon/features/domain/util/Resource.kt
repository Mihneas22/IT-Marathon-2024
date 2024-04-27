package com.example.itmarathon.features.domain.util

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<out T>(val result: T): Resource<T>()
    data class Failure(val ex: Exception): Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}